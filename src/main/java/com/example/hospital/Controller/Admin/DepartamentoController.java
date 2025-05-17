package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.Daos.DaoFactory;
import com.example.hospital.Model.Tablas.Compania;
import com.example.hospital.Model.Tablas.Departamento;
import com.example.hospital.Model.Tablas.Laboratorio;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DepartamentoController {
    private Connection connection= ConexioBD.getConnection();
    private Dao<Departamento> departamentoDao = (Dao<Departamento>) DaoFactory.DEPARTAMENTO.crear(connection);

    private String nombre1;
    private String fxml;

    @FXML
    private Button reg;
    @FXML
    private TextField nombre;
    @FXML
    private TextField idbus;
    @FXML
    private TextField piso;

    @FXML
    private TableView<Departamento> tablaAdmin;
    @FXML
    private TableColumn<Departamento, String> idad;
    @FXML
    private TableColumn<Departamento, String> nombread;
    @FXML
    private TableColumn<Departamento, Integer> pisod;

    public DepartamentoController() throws Exception {
    }

    @FXML
    private void buscar() {
        String idBuscado = idbus.getText();

        if (idBuscado.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Debe ingresar un ID para buscar.");
            alert.showAndWait();
            return;
        }

        try {
            Departamento departamento = departamentoDao.consultar(idBuscado);

            if (departamento != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resultado de búsqueda");
                alert.setHeaderText("Departamento encontrado");
                alert.setContentText("ID: " + departamento.getIdDepartamento() + "\n" +
                        "Nombre: " + departamento.getDescripcion() + "\n" +
                        "Piso: " + departamento.getPiso());
                alert.showAndWait();
                idbus.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resultado de búsqueda");
                alert.setHeaderText("Sin resultados");
                alert.setContentText("No se encontró ningún departamento con el ID especificado.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Falló la búsqueda");
            alert.setContentText("Ocurrió un error durante la búsqueda: " + e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private Button agre;
    @FXML
    private void modificar() {
        String idBuscado = idbus.getText();

        if (idBuscado.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Debe ingresar un ID para buscar y modificar.");
            alert.showAndWait();
            return;
        }

        try {
            Departamento departamento = departamentoDao.consultar(idBuscado);

            if (departamento != null) {
                // Populate fields with department data
                nombre.setText(departamento.getDescripcion());
                piso.setText(String.valueOf(departamento.getPiso()));
                nombre.setDisable(false);
                piso.setDisable(false);
                idbus.setDisable(true);
                // Change button text to indicate "modify"
                agre.setText("Modificar");

                // Validate and modify department details
                agre.setOnAction(event -> {
                    String nuevoNombre = nombre.getText();
                    String nuevoPiso = piso.getText();

                    if (nuevoNombre.isEmpty() || nuevoPiso.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Advertencia");
                        alert.setHeaderText("Campos vacíos");
                        alert.setContentText("Debe completar todos los campos para modificar.");
                        alert.showAndWait();
                        return;
                    }

                    try {
                        int nuevoPisoNumerico = Integer.parseInt(nuevoPiso);

                        departamento.setDescripcion(nuevoNombre);
                        departamento.setPiso(nuevoPisoNumerico);

                        boolean actualizado = departamentoDao.modificar(departamento);
                        if (actualizado) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Éxito");
                            alert.setHeaderText("Modificación exitosa");
                            alert.setContentText("El departamento fue modificado correctamente.");
                            alert.showAndWait();
                            nombre.setText("");
                            piso.setText("");
                            nombre.setDisable(true);
                            piso.setDisable(true);
                            idbus.setDisable(false);
                            idbus.setText("");
                            // Reset button text after modifying
                            agre.setText("Buscar");
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Modificación fallida");
                            alert.setContentText("No se pudo modificar el departamento.");
                            alert.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("El piso debe ser un número válido");
                        alert.setContentText("Por favor, ingrese un valor numérico para el piso.");
                        alert.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error inesperado");
                        alert.setContentText("Ocurrió un error durante la modificación: " + e.getMessage());
                        alert.showAndWait();
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No encontrado");
                alert.setHeaderText("Departamento no encontrado");
                alert.setContentText("No existe un departamento con el ID especificado.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Fallo en la búsqueda");
            alert.setContentText("Ocurrió un error al buscar el departamento: " + e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void eliminar() {
        String idBuscado = idbus.getText();

        if (idBuscado.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Debe ingresar un ID para eliminar.");
            alert.showAndWait();
            return;
        }

        try {
            Departamento departamento = departamentoDao.consultar(idBuscado);

            if (departamento != null) {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Confirmación");
                confirmAlert.setHeaderText("¿Desea eliminar este departamento?");
                confirmAlert.setContentText("ID: " + departamento.getIdDepartamento() + "\n" +
                        "Nombre: " + departamento.getDescripcion() + "\n" +
                        "Piso: " + departamento.getPiso() + "\n\n" +
                        "¿Está seguro de que desea eliminar este departamento?");
                if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                    boolean eliminado = departamentoDao.eliminar(idBuscado);

                    if (eliminado) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Éxito");
                        alert.setHeaderText("Departamento eliminado");
                        alert.setContentText("El departamento con ID " + idBuscado + " fue eliminado exitosamente.");
                        alert.showAndWait();
                        idbus.clear();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("No encontrado");
                        alert.setHeaderText("No se encontró el departamento");
                        alert.setContentText("No existe un departamento con el ID especificado.");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No encontrado");
                alert.setHeaderText("No se encontró el departamento");
                alert.setContentText("No existe un departamento con el ID especificado.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Falló la eliminación");
            alert.setContentText("Ocurrió un error durante la eliminación: " + e.getMessage());
            alert.showAndWait();
        }
    }


    public void inizializar() {
        try {
            // Load data from the database using the DAO
            var laboratorios = departamentoDao.listar();

            // Set the cell value factories
            idad.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIdDepartamento()));
            nombread.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
            pisod.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPiso()));

            // Bind the data to the TableView
            tablaAdmin.setItems(laboratorios);
        } catch (Exception e) {
            e.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setHeaderText("Error al inicializar");
            alert.setContentText("No se pudo cargar los datos de los departamentos: " + e.getMessage());
            alert.showAndWait();
        }
    }


    public void setText(String text, String fxml) {
        this.nombre1=text;
        this.fxml=fxml+"-view.fxml";
    }

    @FXML
    private void agregar(){
        String nombre = this.nombre.getText();
        String piso = this.piso.getText();
        if (nombre.equals("")) {
            this.nombre.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Debe ingresar un nombre");
            alert.showAndWait();
            
            return;
        }
        if (piso.equals("")) {
            this.piso.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Debe ingresar un piso");
            alert.showAndWait();
            
            return;
        }
        
        try {
            int numeroPiso = Integer.parseInt(piso); // Comprobar que el piso sea un número
            Departamento nuevoDepartamento = new Departamento(generarNuevoDepartamentoId(), nombre, numeroPiso);
            if(departamentoDao.insertar(nuevoDepartamento)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText("Operación completada");
                alert.setContentText("El departamento se agregó correctamente");
                alert.showAndWait();
                this.nombre.setText("");
                this.piso.setText("");
            }

            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("El campo 'piso' debe contener un número válido");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Hubo un problema al agregar el departamento");
            alert.showAndWait();
        }
        
        
    }
    @FXML
    public void regresar() {
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar(fxml,nombre1);
            reg.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generarNuevoDepartamentoId() {
        String nuevoId = "DEP01";
        String sql = "SELECT IDDepartamento FROM Departamento ORDER BY IDDepartamento DESC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String ultimoId = rs.getString("IDDepartamento");
                if (ultimoId != null && ultimoId.startsWith("DEP") && ultimoId.length() == 5) {
                    int num = Integer.parseInt(ultimoId.substring(3)) + 1;
                    nuevoId = String.format("DEP%02d", num);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevoId;
    }

}
