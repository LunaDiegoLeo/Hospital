package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.*;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
            ArrayList<Adminis> adm= adminDao.listar();
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

    public void setText(String text, String fxml) {
        this.nombre1=text;
        this.fxml=fxml+"-view.fxml";
    }
}
