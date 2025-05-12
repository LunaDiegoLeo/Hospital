package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.*;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.ArrayList;

public class AdminInsController {
    private Connection connection= ConexioBD.getConnection();
    private Dao<Adminis> adminDao = (Dao<Adminis>) DaoFactory.ADMIN.crear(connection);
    private Dao<Usuario> usuariodao = (Dao<Usuario>) DaoFactory.USUARIO.crear(connection);
    private String nombre1;
    private String fxml;

    @FXML
    private TextField nombre;
    @FXML
    private TextField usuario;
    @FXML
    private TextField pass;
    @FXML
    private Button reg;
    @FXML
    private TableView<Adminis> tablaAdmin;
    @FXML
    private TableColumn<Adminis, String> idad;
    @FXML
    private TableColumn<Adminis, String> nombread;
    @FXML
    private TextField idbus;
    @FXML
    private Button buscar;
    @FXML
    private Label oculto;

    public AdminInsController() throws Exception {
    }

    @FXML
    public void agregar() {
        String nombre=this.nombre.getText();
        String usuario=this.usuario.getText();
        String pass=this.pass.getText();
        if(nombre.equals("")){
            this.nombre.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Debe ingresar un nombre");
            alert.showAndWait();
            return;
        }
        if(usuario.equals("")){
            this.usuario.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Debe ingresar un usuario");
            alert.showAndWait();
            return;
        }
        if(pass.equals("")){
            this.pass.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Debe ingresar una contraseña");
            alert.showAndWait();
            return;
        }

        Usuario u= usuariodao.consultar(usuario);
        if (u == null) {
            ObservableList<Adminis> adm= adminDao.listar();
            int id=adm.size()+1;
            String ida="ADMIN";
            String aux=id+"";
            for(int i=0;i<(3-aux.length());i++){
                ida+="0";
            }
            ida+=id;
            Usuario usuario1= new Usuario(usuario,pass,ida,1);
            Adminis ad= new Adminis(ida,nombre);
            boolean b1= usuariodao.insertar(usuario1);
            boolean b= adminDao.insertar(ad);
            if (b&&b1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Agregado");
                alert.setContentText("Se ha agregado el administrador con exito con un id de: "+ida+"\nY nombre de usuario: "+usuario);
                alert.showAndWait();
                regresar();

            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("Ha ocurrido un error al insertar el administrador");
                alert.showAndWait();
            }
        } else{
            this.usuario.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("El usuario ya existe");
            alert.showAndWait();
        }

    }

    public void inizializar() {
        idad.setCellValueFactory(new PropertyValueFactory<>("id"));
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
        String id=idbus.getText();
        if(!verificar()){
            return;
        }else{
            Adminis ad= adminDao.consultar(id);
            if(ad==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("No se ha encontrado el administrador con el id: "+id);
                alert.showAndWait();
            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buscar");
                alert.setContentText("Se ha encontrado el administrador con el id: "+id+"\nY nombre: "+ad.getNombre());
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void eliminar() {
        String id=idbus.getText();
        if(!verificar()){
            return;
        }else{
            Adminis ad= adminDao.consultar(id);
            if(ad==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("No se ha encontrado el administrador con el id: "+id);
                alert.showAndWait();
            } else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Eliminar");
                alert.setHeaderText("Se ha encontrado el administrador con el id: "+id+"\nY nombre: "+ad.getNombre());
                alert.setContentText("¿Desea eliminarlo?");
                alert.showAndWait();
                if(alert.getResult()==ButtonType.CANCEL){
                    return;
                } else{
                    boolean b1= usuariodao.eliminar(id);
                    boolean b= adminDao.eliminar(id);
                    if(b&&b1){
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Eliminar");
                        alert2.setContentText("Se ha eliminado el administrador con el id: "+id);
                        alert2.showAndWait();
                        idbus.setText("");
                    } else{
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setHeaderText("Ha ocurrido un error");
                        alert2.setContentText("Ha ocurrido un error al eliminar el administrador");
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
        String id=idbus.getText();
        if(!verificar()){
            return;
        } else{
            Adminis ad= adminDao.consultar(id);
            if(ad==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("No existe el administrador con el id: "+id);
                alert.showAndWait();
            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modificar");
                alert.setContentText("Se ha encontrado el usuario con el id: "+id+"\nY nombre: "+ad.getNombre());
                alert.showAndWait();
                nombre.setText(ad.getNombre());
                oculto.setVisible(true);
                nombre.setEditable(true);
                nombre.setVisible(true);
                nombre.requestFocus();
                idbus.setEditable(false);
                buscar.setText("Modificar");
                buscar.setOnAction(event -> {
                    String nombre=this.nombre.getText();
                    if(nombre.equals("")){
                        this.nombre.requestFocus();
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Error");
                        alert1.setHeaderText("Ha ocurrido un error");
                        alert1.setContentText("Debe ingresar un nombre");
                        alert1.showAndWait();

                    } else{
                        ad.setNombre(nombre);
                        boolean b= adminDao.modificar(ad);
                        if (b){
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Modificar");
                            alert1.setContentText("Se ha modificado el administrador con el id: "+id);
                            alert1.showAndWait();
                            regresar();
                        } else{
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Error");
                            alert1.setHeaderText("Ha ocurrido un error");
                            alert1.setContentText("Ha ocurrido un error al modificar el administrador");
                            alert1.showAndWait();
                        }
                    }
                });


            }
        }
    }

    public void setText(String text, String fxml) {
        this.nombre1=text;
        this.fxml=fxml+"-view.fxml";
    }
}
