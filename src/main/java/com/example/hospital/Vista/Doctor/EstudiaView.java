package com.example.hospital.Vista.Doctor;

import com.example.hospital.Controller.Doctor.EstudiarController;
import com.example.hospital.Controller.Doctor.IntervencionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EstudiaView {
    public void mostrar(String text, String id, String idpa) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("estudiar.fxml"));
        Parent root = loader.load();
        EstudiarController controller = loader.getController();
        controller.setText(text,id, idpa);
        Stage stage = new Stage();
        stage.setTitle("Panel Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
