package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.LaboratorioDao;
import com.example.hospital.Model.Tablas.Laboratorio;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LaboratorioController {
    private Connection connection= ConexioBD.getConnection();
    private LaboratorioDao laboratorioDao= (LaboratorioDao) DaoFactory.LABORATORIO.crear(connection);


    private String nombre1;
    private String fxml;
    @FXML
    private TextField nombre;
    @FXML
    private TextField piso;
    @FXML
    private TableView<Laboratorio> tablaAdmin;
    @FXML
    private TableColumn<Laboratorio, String> idad;
    @FXML
    private TableColumn<Laboratorio, String> nombread;
    @FXML
    private TableColumn<Laboratorio, Integer> pisod;

    @FXML
    private javafx.scene.control.Button reg;

    public LaboratorioController() throws Exception {
    }

    public void inizializar() {
        try {
            // Load data from the database using the DAO
            var laboratorios = laboratorioDao.listar();

            // Set the cell value factories
            idad.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIdLaboratorio()));
            nombread.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
            pisod.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPiso()));

            // Bind the data to the TableView
            tablaAdmin.setItems(laboratorios);
        } catch (Exception e) {
            e.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setHeaderText("Error al inicializar");
            alert.setContentText("No se pudo cargar los datos de los laboratorios: " + e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void eliminar() {
        try {
            // Retrieve the laboratory ID from the name text field
            String laboratorioId = nombre.getText().trim();

            // Validate the input
            if (laboratorioId.isEmpty()) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Campo Vacío");
                alert.setContentText("El ID del laboratorio es requerido para eliminar.");
                alert.showAndWait();
                return;
            }

            // Use laboratorioDao to fetch the existing laboratory
            Laboratorio laboratorio = laboratorioDao.consultar(laboratorioId);
            if (laboratorio == null) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("No Encontrado");
                alert.setContentText("No se encontró ningún laboratorio con el ID: " + laboratorioId);
                alert.showAndWait();
                return;
            }

            // Display laboratory details and ask for confirmation
            javafx.scene.control.Alert confirmAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            confirmAlert.setHeaderText("Confirmar Eliminación");
            confirmAlert.setContentText("ID: " + laboratorio.getIdLaboratorio() +
                    "\nDescripcion: " + laboratorio.getDescripcion() +
                    "\nPiso: " + laboratorio.getPiso() +
                    "\n\n¿Seguro que desea eliminar este laboratorio?");

            var result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == javafx.scene.control.ButtonType.OK) {
                // Attempt to delete the laboratory
                boolean deleted = laboratorioDao.eliminar(laboratorioId);
                if (deleted) {
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Éxito");
                    alert.setContentText("El laboratorio ha sido eliminado correctamente.");
                    alert.showAndWait();

                    // Clear the text fields
                    nombre.clear();
                } else {
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al eliminar");
                    alert.setContentText("No se pudo eliminar el laboratorio. Por favor, inténtelo de nuevo.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setHeaderText("Excepción");
            alert.setContentText("Ocurrió un error: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private TextField idbus;
    @FXML
    private Button agre; // Now used for "Modificar" functionality
    
    @FXML
    private void modificar() {
        try {
            // Retrieve the laboratory ID from the idbus field
            String laboratorioId = idbus.getText().trim();

            // Validate input
            if (laboratorioId.isEmpty()) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Campo vacío");
                alert.setContentText("El ID del laboratorio es requerido para buscar y modificar.");
                alert.showAndWait();
                return;
            }

            // Fetch the laboratory details using Dao
            Laboratorio laboratorio = laboratorioDao.consultar(laboratorioId);
            if (laboratorio == null) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("No Encontrado");
                alert.setContentText("No se encontró ningún laboratorio con el ID: " + laboratorioId);
                alert.showAndWait();
                return;
            }

            // Populate the fields with the laboratory details
            nombre.setText(laboratorio.getDescripcion());
            piso.setText(String.valueOf(laboratorio.getPiso()));

            // Make fields editable (except for ID)
            nombre.setDisable(false);
            piso.setDisable(false);
            idbus.setDisable(true);

            // Update the button label to indicate "Save Modifications"
            agre.setText("Guardar Cambios");

            // Set button behavior to save changes
            agre.setOnAction(event -> {
                try {
                    // Extract updated data from fields
                    String updatedNombre = nombre.getText().trim();
                    String updatedPiso = piso.getText().trim();

                    // Validate input
                    if (updatedNombre.isEmpty() || !updatedPiso.matches("\\d+")) {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                        alert.setHeaderText("Error de entrada");
                        alert.setContentText("Por favor, asegúrese de que todos los campos sean válidos (nombre y piso).");
                        alert.showAndWait();
                        return;
                    }

                    // Update the laboratory object
                    laboratorio.setDescripcion(updatedNombre);
                    laboratorio.setPiso(Integer.parseInt(updatedPiso));

                    // Save changes using DAO
                    boolean updated = laboratorioDao.modificar(laboratorio);
                    if (updated) {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Éxito");
                        alert.setContentText("Laboratorio actualizado correctamente.");
                        alert.showAndWait();

                        // Clear fields and reset button
                        nombre.clear();
                        piso.clear();
                        idbus.clear();
                        nombre.setDisable(true);
                        piso.setDisable(true);
                        idbus.setDisable(false);
                        agre.setText("Modificar");
                        agre.setOnAction(e -> modificar());
                    } else {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                        alert.setHeaderText("Error al guardar");
                        alert.setContentText("No se pudo modificar el laboratorio. Por favor, inténtelo de nuevo.");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setHeaderText("Excepción");
                    alert.setContentText("Ocurrió un error: " + e.getMessage());
                    alert.showAndWait();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setHeaderText("Excepción");
            alert.setContentText("Ocurrió un error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void agregar() {
        try {
            // Extract data from text fields
            String laboratorioNombre = nombre.getText();
            String laboratorioPiso = piso.getText();

            // Validate input fields
            if (laboratorioNombre == null || laboratorioNombre.trim().isEmpty()) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Campo Vacío");
                alert.setContentText("El nombre del laboratorio es requerido.");
                alert.showAndWait();
                return;
            }

            if (laboratorioPiso == null || !laboratorioPiso.matches("\\d+")) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Piso Inválido");
                alert.setContentText("El piso debe ser un número.");
                alert.showAndWait();
                return;
            }

            // Create a new Laboratory object and set its values
            String nuevoId = generarNuevoIdLaboratorio();
            var laboratorio = new com.example.hospital.Model.Tablas.Laboratorio();
            laboratorio.setIdLaboratorio(nuevoId);
            laboratorio.setDescripcion(laboratorioNombre.trim());
            laboratorio.setPiso(Integer.parseInt(laboratorioPiso));

            // Insert into database using LaboratorioDao
            boolean success = laboratorioDao.insertar(laboratorio);
            if (success) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setHeaderText("Éxito");
                alert.setContentText("Laboratorio agregado correctamente con ID: " + nuevoId);
                alert.showAndWait();

                // Clear text fields
                nombre.clear();
                piso.clear();
            } else {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("Error al guardar");
                alert.setContentText("No se pudo agregar el laboratorio. Por favor, inténtelo de nuevo.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setHeaderText("Excepción");
            alert.setContentText("Ocurrió un error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    protected void buscar() {
        try {
            // Retrieve the laboratory ID from the text field
            String laboratorioId = nombre.getText().trim();

            // Validate the provided laboratory ID
            if (laboratorioId.isEmpty()) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Campo Vacío");
                alert.setContentText("El ID del laboratorio es requerido para buscar.");
                alert.showAndWait();
                return;
            }

            // Use the laboratorioDao to fetch the laboratory
            Laboratorio laboratorio = laboratorioDao.consultar(laboratorioId);
            if (laboratorio != null) {
                // Display the laboratory details in an informational alert
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setHeaderText("Laboratorio Encontrado");
                alert.setContentText("ID del Laboratorio: " + laboratorio.getIdLaboratorio() +
                        "\nDescripcion: " + laboratorio.getDescripcion() +
                        "\nPiso: " + laboratorio.getPiso());
                alert.showAndWait();
            } else {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setHeaderText("No Encontrado");
                alert.setContentText("No se encontró ningún laboratorio con el ID: " + laboratorioId);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Ocurrió un error al buscar: " + e.getMessage());
            alert.showAndWait();
        }
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
    private String generarNuevoIdLaboratorio() {
        String nuevoId = "LAB01";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT idLaboratorio FROM laboratorio ORDER BY idLaboratorio DESC LIMIT 1")) {
            if (rs.next()) {
                String ultimoId = rs.getString("idLaboratorio");
                int num = Integer.parseInt(ultimoId.substring(3)) + 1;
                nuevoId = String.format("LAB%02d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevoId;
    }


    public void setText(String text, String fxml) {
        this.nombre1 = text;
        this.fxml = fxml + "-view.fxml";
    }
}
