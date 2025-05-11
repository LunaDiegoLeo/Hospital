package com.example.hospital.Controller;

import com.example.hospital.Model.Usuario;
import com.example.hospital.Model.UsuarioBd;
import com.example.hospital.Vista.Admin.AdminView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private PasswordField pass;
    @FXML
    private TextField usuario;
    @FXML
    private Button logbo;

    @FXML
    protected void onHelloButtonClick() {
        try {
            UsuarioBd usuarioBd = new UsuarioBd();
            Usuario usuario1= usuarioBd.buscarUsuario(usuario.getText(),pass.getText());
            if(usuario1==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("Usuario y/o contraseña incorrectos");
                alert.showAndWait();
            } else{
                AdminView adminView = new AdminView();
                adminView.mostrar(usuario1.getUsuario());

                logbo.getScene().getWindow().hide();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}