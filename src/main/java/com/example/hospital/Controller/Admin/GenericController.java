package com.example.hospital.Controller.Admin;

import com.example.hospital.Vista.Admin.AdminView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GenericController {
    @FXML
    private Button regresar;

    @FXML
    public void regresar() {
        try{
            AdminView adminView = new AdminView();
            adminView.mostrar();

            regresar.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
