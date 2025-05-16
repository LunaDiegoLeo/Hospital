package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.Daos.DaoFactory;
import com.example.hospital.Model.Daos.EstudioDao;
import com.example.hospital.Model.Daos.HaceDao;
import com.example.hospital.Model.Daos.LaboratorioDao;
import com.example.hospital.Model.Tablas.Adminis;
import com.example.hospital.Model.Tablas.Estudio;
import com.example.hospital.Model.Tablas.Hace;
import com.example.hospital.Model.Tablas.Laboratorio;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.HashMap;

public class EstudioController {
    Connection conection = ConexioBD.getConnection();
    LaboratorioDao laboratorioDao = (LaboratorioDao) DaoFactory.LABORATORIO.crear(conection);
    HaceDao haceDao = (HaceDao) DaoFactory.HACE.crear(conection);
    EstudioDao estudioDao = (EstudioDao) DaoFactory.ESTUDIO.crear(conection);

    @FXML
    private TextField des;
    @FXML
    private TextField costo;
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button reg;
    @FXML
    private Button agre;
    @FXML
    private TableView<Estudio> tablaAdmin;
    @FXML
    private TableColumn<Estudio, String> idad;
    @FXML
    private TableColumn<Estudio, String> nombread;
    @FXML
    private TableColumn<Estudio, String> costod;
    @FXML
    private TableColumn<Estudio, String> haced;



    public void inicializarTabla() {
        // Configurar columnas
        idad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdEstudio())));
        nombread.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        costod.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCosto())));
        haced.setCellValueFactory(cellData -> {
            String laboratorios = cellData.getValue().getIdLaboratorio();
            return new SimpleStringProperty(laboratorios != null ? laboratorios : "Sin laboratorio");
        });

        // Llenar datos usando el DAO
        ObservableList<Estudio> estudios = estudioDao.listar();
        tablaAdmin.setItems(estudios);
    }


    public String nombre1;
    public String fxml;

    public EstudioController() throws Exception {
    }
    public void setText(String text, String fxml) {
        this.nombre1=text;
        this.fxml=fxml+"-view.fxml";
    }

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

    private Map<CheckBox, Laboratorio> checkboxLaboratorios = new HashMap<>();

    @FXML
    public void initialize() {
        if(anchor!=null) {
            cargarLaboratorios();
        }
        if(tablaAdmin!=null) {
            inicializarTabla();
        }

    }

    private void cargarLaboratorios() {
        ObservableList<Laboratorio> laboratorios = laboratorioDao.listar();

        double yOffset = 10; // Separación vertical inicial
        for (Laboratorio lab : laboratorios) {
            CheckBox checkBox = new CheckBox(lab.getIdLaboratorio() + " - " + lab.getDescripcion());
            checkBox.setLayoutX(10);
            checkBox.setLayoutY(yOffset);

            // Guardar referencia
            checkboxLaboratorios.put(checkBox, lab);

            anchor.getChildren().add(checkBox);

            yOffset += 30; // Incrementa para el siguiente checkbox
        }
    }


    @FXML
    private void agregar() {
        try {
            // Validar campos
            if (des.getText().isEmpty() || costo.getText().isEmpty()) {
                System.out.println("Complete todos los campos");
                return;
            }

            // Obtener el siguiente ID de Estudio (sencillo, contar + 1)
            int nuevoID = 1;
            String query = "SELECT MAX(idEstudio) AS maxId FROM Estudio";
            try (Statement statement = conection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    nuevoID = resultSet.getInt("maxId") + 1;
                }
            }

            // Crear y guardar el Estudio
            String descripcion = des.getText();
            double costoValor = Double.parseDouble(costo.getText());

            var estudio = new com.example.hospital.Model.Tablas.Estudio(nuevoID, descripcion, costoValor,"");

            boolean insertadoEstudio = estudioDao.insertar(estudio);
            if (!insertadoEstudio) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al insertar el estudio");
                return;
            }

            // Recolectar laboratorios seleccionados y guardar en Hace
            for (Map.Entry<CheckBox, Laboratorio> entry : checkboxLaboratorios.entrySet()) {
                if (entry.getKey().isSelected()) {
                    var hace = new com.example.hospital.Model.Tablas.Hace(entry.getValue().getIdLaboratorio(), nuevoID);
                    boolean insertadoHace = haceDao.insertar(hace);
                    if (!insertadoHace) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al insertar en Hace para laboratorio: " + entry.getValue().getIdLaboratorio());
                    }
                }
            }
            // Opcionalmente limpiar campos
            des.clear();
            costo.clear();
            
            checkboxLaboratorios.keySet().forEach(cb -> cb.setSelected(false));

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "El estudio se ha agregado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al agregar el estudio");
        }
    }
    @FXML
    private void buscar() {
        String idEstudioStr = idbus.getText().trim();

        if (idEstudioStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe ingresar un ID de Estudio.");
            return;
        }

        try {
            Estudio estudio = estudioDao.consultar(idEstudioStr);

            if (estudio == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "No encontrado", "No se encontró el estudio con ID: " + idEstudioStr);
            } else {
                String info = "ID: " + estudio.getIdEstudio() + "\n" +
                        "Descripción: " + estudio.getDescripcion() + "\n" +
                        "Costo: $" + estudio.getCosto() + "\n" +
                        "Laboratorios: " + (estudio.getIdLaboratorio() != null ? estudio.getIdLaboratorio() : "Sin laboratorios");

                mostrarAlerta(Alert.AlertType.INFORMATION, "Estudio encontrado", info);
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al buscar el estudio.");
        } finally {
            idbus.clear(); // Limpia el campo al final, siempre
        }
    }

    @FXML
    private void buscar2() {
        String idEstudioStr = idbus.getText();
        if (idEstudioStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Por favor ingresa el ID del estudio.");
            return;
        }

        Estudio estudio = estudioDao.consultar(idEstudioStr);
        if (estudio != null) {
            des.setText(estudio.getDescripcion());
            costo.setText(String.valueOf(estudio.getCosto()));

            // Marcar los laboratorios actuales
            ObservableList<String> idLaboratoriosActuales = haceDao.listarLaboratoriosPorEstudio(Integer.parseInt(idEstudioStr));
            for (CheckBox cb : checkboxLaboratorios.keySet()) {
                cb.setSelected(idLaboratoriosActuales.contains(checkboxLaboratorios.get(cb).getIdLaboratorio()));
            }

            des.setDisable(false);
            costo.setDisable(false);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Estudio encontrado", "Modifica los campos y presiona Modificar.");

            // Cambiar texto y acción del botón
            agre.setText("Modificar");
            agre.setOnAction(e -> modificar());
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el estudio con ID: " + idEstudioStr);
        }
    }

    @FXML
    private void modificar() {
        String idEstudioStr = idbus.getText();
        if (idEstudioStr.isEmpty() || des.getText().isEmpty() || costo.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Todos los campos deben estar llenos.");
            return;
        }

        try {
            Estudio estudio = new Estudio(
                    Integer.parseInt(idEstudioStr),
                    des.getText(),
                    Double.parseDouble(costo.getText()),
                    "" // No se usa aquí
            );

            if (estudioDao.modificar(estudio)) {
                // Primero eliminamos relaciones actuales
                haceDao.eliminar(estudio.getIdEstudio()+"");

                // Insertamos nuevas relaciones según checkboxes seleccionados
                for (Map.Entry<CheckBox, Laboratorio> entry : checkboxLaboratorios.entrySet()) {
                    if (entry.getKey().isSelected()) {
                        Hace hace = new Hace(entry.getValue().getIdLaboratorio(), estudio.getIdEstudio());
                        haceDao.insertar(hace);
                    }
                }

                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudio y laboratorios actualizados correctamente.");

                // Resetear campos y botón
                limpiarCampos();
                agre.setText("Buscar");
                agre.setOnAction(e -> buscar2());

            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo modificar el estudio.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Costo debe ser un número válido.");
        }
    }

    private void limpiarCampos() {
        idbus.clear();
        des.clear();
        costo.clear();
        des.setDisable(true);
        costo.setDisable(true);

        // Desmarcar todos los laboratorios
        for (CheckBox cb : checkboxLaboratorios.keySet()) {
            cb.setSelected(false);
        }
    }





    @FXML
    private TextField idbus;

    @FXML
    private void eliminar() {
        String idEstudioStr = idbus.getText().trim();

        if (idEstudioStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe ingresar un ID de Estudio.");
            return;
        }

        try {
            Estudio estudio = estudioDao.consultar(idEstudioStr);

            if (estudio == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el estudio con ID: " + idEstudioStr);
                return;
            }

            // Mostrar información encontrada
            String info = "ID: " + estudio.getIdEstudio() + "\n" +
                    "Descripción: " + estudio.getDescripcion() + "\n" +
                    "Costo: $" + estudio.getCosto() + "\n" +
                    "Laboratorios: " + (estudio.getIdLaboratorio() != null ? estudio.getIdLaboratorio() : "Sin laboratorios");

            // Confirmación con Alert
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmación de eliminación");
            confirmacion.setHeaderText("¿Desea eliminar el siguiente estudio?");
            confirmacion.setContentText(info);

            // Mostrar y esperar respuesta
            if (confirmacion.showAndWait().get() == ButtonType.OK) {
                boolean haceEliminado = haceDao.eliminar(estudio.getIdEstudio()+"");
                boolean estudioEliminado = estudioDao.eliminar(estudio.getIdEstudio()+"");

                if (estudioEliminado) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudio eliminado correctamente.");
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al eliminar el estudio.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error durante la eliminación.");
        }
    }
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
