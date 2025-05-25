package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.*;
import com.example.hospital.Model.Daos.*;
import com.example.hospital.Model.Tablas.Medicamento;
import com.example.hospital.Model.Tablas.Presentacion;
import com.example.hospital.Model.Tablas.SePresenta;
import com.example.hospital.Model.Tablas.TipoMedicamento;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static com.example.hospital.Model.ConexioBD.connection;

public class MedicamentoController {

    Connection conection = ConexioBD.getConnection();

    private MedicamentoDao medicamentoDao = (MedicamentoDao) DaoFactory.MEDICAMENTO.crear(connection);
    private TipoMedicamentoDao tipoDao = (TipoMedicamentoDao) DaoFactory.TIPOMEDICAMENTO.crear(connection);
    private PresentacionDao presentacionDao = (PresentacionDao) DaoFactory.PRESENTACION.crear(connection);
    private SePresentaDao sePresentaDao = (SePresentaDao) DaoFactory.SEPRESENTA.crear(connection);

    public String nombre1;
    public String fxml;

    @FXML
    private Button reg, agre;

    @FXML
    private TextField nombre, descripcion, cantidad, costo;

    @FXML
    private SplitMenuButton tipos, presentaciones;

    private TipoMedicamento tipoSeleccionado;
    private Presentacion presentacionSeleccionada;

    public MedicamentoController() throws Exception {
    }

    public void setText(String text, String fxml) {
        this.nombre1 = text;
        this.fxml = fxml + "-view.fxml";
    }

    @FXML
    private TableView<Medicamento> tablaMedicamentos;
    @FXML
    private TableColumn<Medicamento, String> idMedCol;
    @FXML
    private TableColumn<Medicamento, String> nombreMedCol;
    @FXML
    private TableColumn<Medicamento, String> descMedCol;

    @FXML
    private void initialize() {
        if (tipos != null) {
            cargarTipos();

        }
        if (presentaciones != null) {
            cargarPresentaciones();

        }
        if (tablaMedicamentos != null) {
            idMedCol.setCellValueFactory(new PropertyValueFactory<>("idMedicamento"));
            nombreMedCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            descMedCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));


            cargarMedicamentos();
        }

    }

    private void cargarMedicamentos() {
        try {
            List<Medicamento> lista = medicamentoDao.listar(); // Cambia según tu DAO
            ObservableList<Medicamento> listaObservable = FXCollections.observableArrayList(lista);
            tablaMedicamentos.setItems(listaObservable);
        } catch (Exception e) {
            e.printStackTrace();
            // Podrías mostrar una alerta de error si quieres
        }
    }

    @FXML
    private TextField busqueda;

    @FXML
    private void buscar() {
        String criterio = busqueda.getText().trim();
        if (criterio.isEmpty()) {
            showAlert("Advertencia", "Por favor, ingresa un nombre o ID para buscar.");
            return;
        }

        try {

            Medicamento medicamento = medicamentoDao.buscarPorNombreOId(criterio);

            if (medicamento == null) {
                showAlert("Resultado", "No se encontró medicamento con ese criterio.");
            } else {

                System.out.println("Medicamento encontrado: " + medicamento.getNombre() + ", " + medicamento.getDescripcion());

            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Ocurrió un error durante la búsqueda.");
        }
    }



    private void cargarTipos() {
        ObservableList<TipoMedicamento> tipos = tipoDao.listar();
        for (TipoMedicamento tipo : tipos) {
            MenuItem item = new MenuItem(tipo.getDescripcion());
            item.setOnAction(e -> {
                this.tipos.setText(tipo.getDescripcion());
                tipoSeleccionado = tipo;
            });
            this.tipos.getItems().add(item);
        }
    }

    private void cargarPresentaciones() {
        ObservableList<Presentacion> presentaciones = presentacionDao.listar();
        for (Presentacion presentacion : presentaciones) {
            String texto = presentacion.getCantidadUnidad() + " " + presentacion.getUnidad();
            MenuItem item = new MenuItem(texto);
            item.setOnAction(e -> {
                this.presentaciones.setText(texto);
                presentacionSeleccionada = presentacion;
            });
            this.presentaciones.getItems().add(item);
        }
    }

    @FXML
    protected void agregar() {
        try {
            if (nombre.getText().isEmpty() || descripcion.getText().isEmpty() || tipoSeleccionado == null || presentacionSeleccionada == null
                    || cantidad.getText().isEmpty() || costo.getText().isEmpty()) {
                showAlert("Error", "Todos los campos deben estar completos.");
                return;
            }

            Medicamento medicamento = medicamentoDao.buscarPorNombre(nombre.getText());

            if (medicamento == null) {
                // No existe, crear medicamento
                medicamento = new Medicamento(generarNuevoId(), nombre.getText(), descripcion.getText());

                if (!medicamentoDao.insertar(medicamento)) {
                    showAlert("Error", "No se pudo insertar el medicamento.");
                    return;
                }
            }

            // Crear relación en SePresenta
            SePresenta sePresenta = new SePresenta();
            sePresenta.setIdMedicamento(medicamento.getIdMedicamento());
            sePresenta.setIdTipoM(tipoSeleccionado.getIdTipoM());
            sePresenta.setIdPresentacion(presentacionSeleccionada.getIdPresentacion());
            sePresenta.setCantidad(Integer.parseInt(cantidad.getText()));
            sePresenta.setCosto(Double.parseDouble(costo.getText()));

            if (!sePresentaDao.insertar(sePresenta)) {
                showAlert("Error", "No se pudo asociar el medicamento.");
                return;
            }

            // Preguntar si quiere agregar otra presentación/tipo para el mismo medicamento
            boolean agregarOtra = showConfirm("Éxito", "Medicamento registrado correctamente.\n¿Deseas agregar otra presentación o tipo para este medicamento?");

            if (agregarOtra) {
                // Solo limpiar presentación, tipo, cantidad y costo para seguir agregando al mismo medicamento
                limpiarCamposPresentacionTipo();
            } else {
                limpiarFormularioCompleto();
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Ocurrió un error al guardar.");
        }
    }

    private boolean showConfirm(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        ButtonType btnSi = new ButtonType("Sí");
        ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnSi, btnNo);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == btnSi;
    }




    private void limpiarFormularioCompleto() {
        nombre.clear();
        descripcion.clear();
        presentaciones.setText("Selecciona una presentación");
        tipos.setText("Selecciona un tipo");
        cantidad.clear();
        costo.clear();
        tipoSeleccionado = null;
        presentacionSeleccionada = null;
    }

    private void limpiarCamposPresentacionTipo() {
        presentaciones.setText("Selecciona una presentación");
        tipos.setText("Selecciona un tipo");
        cantidad.clear();
        costo.clear();
        tipoSeleccionado = null;
        presentacionSeleccionada = null;
    }




    private String generarNuevoId() {
        String nuevoId = "MED000000000001";
        try {
            String ultimo = medicamentoDao.obtenerUltimoId();
            if (ultimo != null) {
                int num = Integer.parseInt(ultimo.substring(3)) + 1;
                nuevoId = String.format("MED%012d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevoId;
    }

    private void limpiarFormulario() {
        nombre.clear();
        descripcion.clear();
        cantidad.clear();
        costo.clear();
        tipos.setText("Selecciona");
        presentaciones.setText("Selecciona");
        tipoSeleccionado = null;
        presentacionSeleccionada = null;
    }
    @FXML
    private TextField txtIdEliminar;
    @FXML
    protected void eliminar() {
        String id = txtIdEliminar.getText().trim();
        if (id.isEmpty()) {
            showAlert("Error", "Por favor ingresa un ID para eliminar.");
            return;
        }

        try {
            // Buscar medicamento por ID
            Medicamento medicamento = medicamentoDao.consultar(id);
            if (medicamento == null) {
                showAlert("Error", "No se encontró medicamento con ID: " + id);
                return;
            }

            // Confirmar eliminación
            boolean confirmado = showConfirm("Confirmar eliminación",
                    "¿Deseas eliminar el medicamento: " + medicamento.getNombre() + " (ID: " + id + ")?");

            if (confirmado) {
                boolean eliminado = medicamentoDao.eliminar(id);
                if (eliminado) {
                    showAlert("Éxito", "Medicamento eliminado correctamente.");
                    txtIdEliminar.clear();
                } else {
                    showAlert("Error", "No se pudo eliminar el medicamento.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Ocurrió un error al intentar eliminar.");
        }
    }

    @FXML
    private TextField txtIdModificar;
      // el botón que hace buscar o guardar según el texto

    // Variable para guardar el medicamento que se busca/modifica
    private Medicamento medicamentoActual;

    @FXML
    private void modificar() {
        String textoBoton = agre.getText();

        if (textoBoton.equals("Buscar")) {
            String id = txtIdModificar.getText().trim();
            if (id.isEmpty()) {
                // Avisar al usuario que ingrese un ID
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor ingresa un ID para buscar.");
                alerta.show();
                return;
            }

            // Supongamos que tienes un método DAO para buscar por id
            medicamentoActual = medicamentoDao.consultar(id);

            if (medicamentoActual == null) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setContentText("No se encontró medicamento con ID: " + id);
                alerta.show();
                return;
            }

            // Si encontró el medicamento, mostrar datos y habilitar campos
            nombre.setText(medicamentoActual.getNombre());
            descripcion.setText(medicamentoActual.getDescripcion());
            nombre.setDisable(false);
            descripcion.setDisable(false);

            // Cambiar texto del botón a Guardar
            agre.setText("Guardar");

        } else if (textoBoton.equals("Guardar")) {
            // Validar campos antes de guardar
            String nuevoNombre = nombre.getText().trim();
            String nuevaDescripcion = descripcion.getText().trim();

            if (nuevoNombre.isEmpty() || nuevaDescripcion.isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText(null);
                alerta.setContentText("Los campos Nombre y Descripción no pueden estar vacíos.");
                alerta.show();
                return;
            }

            // Actualizar objeto medicamentoActual
            medicamentoActual.setNombre(nuevoNombre);
            medicamentoActual.setDescripcion(nuevaDescripcion);

            // Guardar cambios en la base o lista (según implementación DAO)
            boolean actualizado = medicamentoDao.modificar(medicamentoActual);

            if (actualizado) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setContentText("Medicamento actualizado correctamente.");
                alerta.show();

                // Limpiar campos y deshabilitar
                txtIdModificar.clear();
                nombre.clear();
                descripcion.clear();
                nombre.setDisable(true);
                descripcion.setDisable(true);

                // Cambiar texto del botón de nuevo a Buscar
                agre.setText("Buscar");
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setContentText("Error al actualizar el medicamento.");
                alerta.show();
            }
        }
    }



    @FXML
    protected void regresar() {
        try {
            GenericView vista = new GenericView();
            vista.mostrar(fxml, nombre1);
            reg.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
