package com.example.hospital.Vista.Paciente;

import com.example.hospital.Controller.Paciente.CitasController;
import com.example.hospital.Controller.Paciente.PacienteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CitasView {

    public void mostrarCitas(String text, String id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cita.fxml"));
        Parent root = loader.load();
        CitasController controller = loader.getController();
        controller.setText(text,id);
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
