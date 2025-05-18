package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.Daos.DaoFactory;
import com.example.hospital.Model.Daos.IntervencionDao;
import com.example.hospital.Model.Daos.SalaDao;
import com.example.hospital.Model.Tablas.Intervencion;
import com.example.hospital.Model.Tablas.Sala;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;

public class IntervencionController {
    private Connection connection= ConexioBD.getConnection();

    private SalaDao salaDao = (SalaDao) DaoFactory.SALA.crear(connection);
    private IntervencionDao intervencionDao = (IntervencionDao) DaoFactory.INTERVENCION.crear(connection);


    @FXML
    private TextField nombre;
    @FXML
    private TextField desc;
    @FXML
    private SplitMenuButton tipos;
    private Sala salaSeleccionada;

    public IntervencionController() throws Exception {
    }

    @FXML
    private TableView<Intervencion> tablaAdmin;
    @FXML
    private TableColumn<Intervencion, String> idad;
    @FXML
    private TableColumn<Intervencion, String> nombread;
    @FXML
    private TableColumn<Intervencion, String> pisod;
    @FXML
    private TableColumn<Intervencion, String> salacom;

    @FXML
    private void initialize() {
        if (tipos != null) {
            try {
                ObservableList<Sala> salas = salaDao.listar();

                for (Sala sala : salas) {
                    String texto = sala.getIdDepartamento() + " - " + sala.getNumero()+" - "+sala.getTipoSala();
                    MenuItem item = new MenuItem(texto);

                    item.setOnAction(e -> {
                        tipos.setText(texto);
                        salaSeleccionada = sala;
                    });

                    tipos.getItems().add(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Muestra un mensaje si lo deseas
            }
        }

        if (tablaAdmin != null) {
            // Asocia las columnas con los getters
            idad.setCellValueFactory(new PropertyValueFactory<>("idIntervencion"));
            nombread.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            pisod.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            salacom.setCellValueFactory(new PropertyValueFactory<>("numero"));    // Aquí ya tiene "numero - tipoSala"

            try {
                ObservableList<Intervencion> lista = intervencionDao.listar();
                tablaAdmin.setItems(lista);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void eliminar() {
        if (idbus.getText().isEmpty()) {
            showAlert("Error", "Debe llenar el campo ID.");
            return;
        }

        String idToDelete = idbus.getText();
        Intervencion intervencion = intervencionDao.consultar(idToDelete);

        if (intervencion == null) {
            showAlert("Error", "No se encontró una intervención con el ID especificado.");
            return;
        }
        Sala sala = salaDao.consultar(intervencion.getIdDepartamento() + "-" + intervencion.getNumero());

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmar eliminación");
        confirmationAlert.setHeaderText("Detalles de la intervención:");
        confirmationAlert.setContentText(
                "ID: " + intervencion.getIdIntervencion() + "\n" +
                        "Nombre: " + intervencion.getNombre() + "\n" +
                        "Descripción: " + intervencion.getDescripcion() + "\n" +
                        "Sala: " + (sala != null ? sala.getTipoSala() + " (" + sala.getIdDepartamento() + "-" + sala.getNumero() + ")" : "No disponible") + "\n\n" +
                        "¿Está seguro de que desea eliminar la intervención con ID: " + idToDelete + "?"
        );

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    if (intervencionDao.eliminar(idToDelete)) {
                        showAlert("Éxito", "La intervención se eliminó correctamente.");
                        idbus.clear();
                    } else {
                        showAlert("Error", "No se pudo eliminar la intervención.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Ocurrió un error al intentar eliminar la intervención.");
                }
            }
        });
    }
    @FXML
    private Button agre;
    @FXML
    protected void modificar() {
        if (idbus.getText().isEmpty()) {
            showAlert("Error", "Debe llenar el campo ID.");
            return;
        }

        Intervencion intervencion = intervencionDao.consultar(idbus.getText());
        if (intervencion == null) {
            showAlert("Error", "No se encontró la intervención.");
            return;
        }

        Sala sala = salaDao.consultar(intervencion.getIdDepartamento() + "-" + intervencion.getNumero());
        if (sala == null) {
            showAlert("Error", "No se encontró la sala asociada.");
            return;
        }

        // Mostrar datos
        tipos.setText(sala.getIdDepartamento() + " - " + sala.getNumero() + " - " + sala.getTipoSala());
        nombre.setText(intervencion.getNombre());
        desc.setText(intervencion.getDescripcion());

        // Habilitar campos
        tipos.setDisable(false);
        nombre.setDisable(false);
        desc.setDisable(false);
        idbus.setDisable(true);

        // Preparar botón para actualizar
        agre.setText("Modificar");
        agre.setOnAction(e -> {
            try {
                intervencion.setNombre(nombre.getText());
                intervencion.setDescripcion(desc.getText());
                intervencion.setIdDepartamento(salaSeleccionada.getIdDepartamento());
                intervencion.setNumero(salaSeleccionada.getNumero()+"");

                intervencionDao.modificar(intervencion);
                showAlert("Éxito", "Intervención modificada correctamente.");


                // Resetear formulario
                limpiarCampos();
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Error", "No se pudo modificar la intervención.");
            }
        });
    }

    private void limpiarCampos() {
        idbus.clear();
        nombre.clear();
        desc.clear();
        tipos.setText("Selecciona");
        salaSeleccionada = null;

        nombre.setDisable(true);
        desc.setDisable(true);
        tipos.setDisable(true);
        idbus.setDisable(false);

        agre.setText("Buscar");
        agre.setOnAction(e -> {
            modificar();
        });
    }




    @FXML
    protected void agregar() {
        if (salaSeleccionada == null) {
            showAlert("Error", "Debes seleccionar una sala.");
            return;
        }

        String nombreIntervencion = nombre.getText();
        String descripcion = desc.getText();

        if (nombreIntervencion == null || nombreIntervencion.trim().isEmpty()) {
            showAlert("Error", "El nombre de la intervención no puede estar vacío.");
            return;
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            showAlert("Error", "La descripción no puede estar vacía.");
            return;
        }

        try {
            String nuevoId = intervencionDao.generarNuevoIdIntervencion();

            Intervencion nueva = new Intervencion(
                    nuevoId,
                    nombreIntervencion,
                    descripcion,
                    salaSeleccionada.getIdDepartamento(),
                    salaSeleccionada.getNumero() + ""
            );

            if (intervencionDao.insertar(nueva)) {
                showAlert("Éxito", "Intervención agregada exitosamente.\n\nId: "+nuevoId);
                nombre.clear();
                desc.clear();
                tipos.setText("Selecciona");
                salaSeleccionada = null;
            } else {
                showAlert("Error", "Hubo un problema al insertar la intervención.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Ocurrió un error al agregar la intervención.");
        }
    }
    @FXML
    private TextField idbus;
    @FXML
    protected void buscar() {
        if(idbus.getText().isEmpty()){
            showAlert("Error","Debe llenar el campo ID");
            return;
        }
        Intervencion intervencion = intervencionDao.consultar(idbus.getText());
        Sala sala= salaDao.consultar(intervencion.getIdDepartamento()+"-"+intervencion.getNumero());
        if (intervencion==null){
            showAlert("Error","No se encontro el ID en intervenciones");
        } else{
            String texto = "ID: " + intervencion.getIdIntervencion() + "\n" +
                    "Nombre: " + intervencion.getNombre() + "\n" +
                    "Descripción: " + intervencion.getDescripcion() + "\n" +
                    "Sala: " + sala.getTipoSala() + " (" + sala.getIdDepartamento() + "-" + sala.getNumero() + ")";
            showAlert("Información de Intervención", texto);
            idbus.clear();
        }
        
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String nombre1;
    private String fxml;
    public void setText(String text, String fxml) {
        this.nombre1 = text;
        this.fxml = fxml + "-view.fxml";
    }
    @FXML
    private Button reg;

    @FXML
    protected void regresar() {
        try {
            GenericView paciente = new GenericView();
            paciente.mostrar(fxml, nombre1);
            reg.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
