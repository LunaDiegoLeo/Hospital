package com.example.hospital.Vista.Paciente;

import com.example.hospital.Controller.Paciente.ListarInt;
import com.example.hospital.Controller.Paciente.ListarRecetas;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InterView {
    public void mostrarCitas(String text, String id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("intervenciones.fxml"));
        Parent root = loader.load();
        ListarInt controller = loader.getController();
        controller.setText(text,id);
        controller.cargar();
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
