package com.example.hospital.Vista.Doctor;

import com.example.hospital.Controller.Doctor.Doctor;
import com.example.hospital.Controller.Doctor.ListarCitasPo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CitasProxView {

    public void mostrar(String text, String id) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listarcitasprox.fxml"));
        Parent root = loader.load();
        ListarCitasPo controller = loader.getController();
        controller.setText(text,id);
        controller.cargar();
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
