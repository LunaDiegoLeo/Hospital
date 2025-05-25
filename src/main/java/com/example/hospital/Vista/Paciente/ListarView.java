package com.example.hospital.Vista.Paciente;

import com.example.hospital.Controller.Paciente.CitasController;
import com.example.hospital.Controller.Paciente.ListarCitas;
import com.example.hospital.Controller.Paciente.PacienteController;
import com.example.hospital.Controller.Paciente.ReagendarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ListarView {
    public void mostrar(String text, String id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("citas/listarcitas.fxml"));
        Parent root = loader.load();
        ListarCitas controller = loader.getController();
        controller.setText(text,id);
        controller.cargar();
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
