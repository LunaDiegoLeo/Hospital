package com.example.hospital.Vista.Paciente;

import com.example.hospital.Controller.Admin.AdminController;
import com.example.hospital.Controller.Paciente.PacienteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PacienteView {

    public void mostrar(String text, String id) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paciente-se.fxml"));
        Parent root = loader.load();
        PacienteController controller = loader.getController();
        controller.setText(text,id);
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
