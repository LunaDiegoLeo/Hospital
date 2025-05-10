package com.example.hospital.Vista.Admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenericView {
    public void mostrar(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Paciente");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
