package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Vista.Admin.GenericView;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.sql.Connection;

public class AdminController {
    private Connection connection= ConexioBD.getConnection();

    @FXML
    private Button pac;
    public AdminController() throws Exception {
    }

    @FXML
    protected void irAdmin() {
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("admin-se.fxml");

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected  void irPaciente() {
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("paciente-view.fxml");

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
