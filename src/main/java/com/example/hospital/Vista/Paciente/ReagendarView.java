package com.example.hospital.Vista.Paciente;

import com.example.hospital.Controller.Paciente.AgendarCitaController;
import com.example.hospital.Controller.Paciente.ReagendarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReagendarView {
    public void mostrar(String text, String id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("citas/reagendar.fxml"));
        Parent root = loader.load();
        ReagendarController controller = loader.getController();
        controller.setText(text,id);
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
