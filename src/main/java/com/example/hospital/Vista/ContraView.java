package com.example.hospital.Vista;

import com.example.hospital.Controller.ContraController;
import com.example.hospital.Controller.Paciente.PacienteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContraView {
    public void mostrar(String text, String id,Object pa) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("password.fxml"));
        Parent root = loader.load();
        ContraController controller = loader.getController();
        controller.setText(text,id,pa);
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
