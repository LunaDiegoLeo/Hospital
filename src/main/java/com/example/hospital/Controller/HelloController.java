package com.example.hospital.Controller;

import com.example.hospital.Vista.Admin.AdminView;
import javafx.fxml.FXML;
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
            AdminView adminView = new AdminView();
            adminView.mostrar(usuario.getText());

            logbo.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}