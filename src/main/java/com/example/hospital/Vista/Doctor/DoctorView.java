package com.example.hospital.Vista.Doctor;

import com.example.hospital.Controller.Doctor.Doctor;
import com.example.hospital.Controller.Paciente.PacienteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoctorView {
    public void mostrar(String text, String id) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("doctor.fxml"));
        Parent root = loader.load();
        Doctor controller = loader.getController();
        controller.setText(text,id);
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
