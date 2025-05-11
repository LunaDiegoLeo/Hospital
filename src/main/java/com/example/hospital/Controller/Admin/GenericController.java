package com.example.hospital.Controller.Admin;

import com.example.hospital.Vista.Admin.AdminView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GenericController {
    @FXML
    private Button regresar;
    @FXML
    private Label usua;

    @FXML
    public void regresar() {
        try{
            AdminView adminView = new AdminView();
            adminView.mostrar(usua.getText());

            regresar.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setText(String text){
        usua.setText(text);
    }
}
