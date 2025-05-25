package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.*;
import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Tablas.Compania;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CompaniaController{
    private Connection connection= ConexioBD.getConnection();
    private Dao<Compania> adminDao = (Dao<Compania>) DaoFactory.COMPANIA.crear(connection);
    private String nombre1;
    private String fxml;

    @FXML
    private TextField nombre;
    
    @FXML
    private Button reg;
    @FXML
    private TableView<Compania> tablaAdmin;
    @FXML
    private TableColumn<Compania, String> idad;
    @FXML
    private TableColumn<Compania, String> nombread;
    @FXML
    private TextField idbus;
    @FXML
    private Button buscar;
    @FXML
    private Label oculto;

    

    public CompaniaController() throws Exception {
    }

    @FXML
    public void agregar() {
        String nombre = this.nombre.getText();

        if (nombre.equals("")) {
            this.nombre.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Debe ingresar un nombre");
            alert.showAndWait();
            return;
        }

        String nuevoId = generarNuevoCompaniaId();
        Compania nuevaCompania = new Compania(nuevoId, nombre);
        boolean insertado = adminDao.insertar(nuevaCompania);

        if (insertado) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Agregado");
            alert.setContentText("Se ha agregado la compañía con éxito con un ID de: " + nuevoId + "\nY nombre: " + nombre);
            alert.showAndWait();
            this.nombre.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Ha ocurrido un error al insertar la compañía");
            alert.showAndWait();
        }
    }

    public void inizializar() {
        idad.setCellValueFactory(new PropertyValueFactory<>("idCompania"));
        nombread.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tablaAdmin.setItems(adminDao.listar());

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

    @FXML
    public void buscar() {
        String id = idbus.getText();
        if (!verificar()) {
            return;
        } else {
            Compania compania = adminDao.consultar(id);
            if (compania == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("No se ha encontrado la compañía con el id: " + id);
                alert.showAndWait();
                this.idbus.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buscar");
                alert.setContentText("Se ha encontrado la compañía con el id: " + id + "\nY nombre: " + compania.getNombre());
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void eliminar() {
        String id = idbus.getText();
        if (!verificar()) {
            return;
        } else {
            Compania compania = adminDao.consultar(id);
            if (compania == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("No se ha encontrado la compañía con el id: " + id);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Eliminar");
                alert.setHeaderText("Se ha encontrado la compañía con el id: " + id + "\nY nombre: " + compania.getNombre());
                alert.setContentText("¿Desea eliminarla?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.CANCEL) {
                    return;
                } else {
                    boolean eliminado = adminDao.eliminar(id);
                    if (eliminado) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Eliminar");
                        alert2.setContentText("Se ha eliminado la compañía con el id: " + id);
                        alert2.showAndWait();
                        idbus.setText("");
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setHeaderText("Ha ocurrido un error");
                        alert2.setContentText("Ha ocurrido un error al eliminar la compañía");
                        alert2.showAndWait();
                    }
                }
            }
        }
    }

    public boolean verificar(){
        String id=idbus.getText();
        if(id.equals("")){
            this.idbus.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Debe ingresar un id");
            alert.showAndWait();
            return false;
        } else{
            return true;
        }
    }

    @FXML
    public void modificar() {
        String id = idbus.getText();
        if (!verificar()) {
            return;
        } else {
            Compania compania = adminDao.consultar(id);
            if (compania == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("No existe la compañía con el id: " + id);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modificar");
                alert.setContentText("Se ha encontrado la compañía con el id: " + id + "\nY nombre: " + compania.getNombre());
                alert.showAndWait();
                nombre.setText(compania.getNombre());
                oculto.setVisible(true);
                nombre.setEditable(true);
                nombre.setVisible(true);
                nombre.requestFocus();
                idbus.setEditable(false);
                buscar.setText("Modificar");
                buscar.setOnAction(event -> {
                    String nuevoNombre = this.nombre.getText();
                    if (nuevoNombre.equals("")) {
                        this.nombre.requestFocus();
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Error");
                        alert1.setHeaderText("Ha ocurrido un error");
                        alert1.setContentText("Debe ingresar un nombre");
                        alert1.showAndWait();
                    } else {
                        compania.setNombre(nuevoNombre);
                        boolean modificado = adminDao.modificar(compania);
                        if (modificado) {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Modificar");
                            alert1.setContentText("Se ha modificado la compañía con el id: " + id);
                            alert1.showAndWait();
                            regresar();
                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Error");
                            alert1.setHeaderText("Ha ocurrido un error");
                            alert1.setContentText("Ha ocurrido un error al modificar la compañía");
                            alert1.showAndWait();
                        }
                    }
                });
            }
        }
    }

    private String generarNuevoCompaniaId() {
        String nuevoId = "COMP000001";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT idCompañia FROM compañia ORDER BY idCompañia DESC LIMIT 1")) {
            if (rs.next()) {
                String ultimoId = rs.getString("idCompañia");
                int num = Integer.parseInt(ultimoId.substring(4)) + 1;
                nuevoId = String.format("COMP%06d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevoId;
    }

    public void setText(String text, String fxml) {
        this.nombre1=text;
        this.fxml=fxml+"-view.fxml";
    }
}
