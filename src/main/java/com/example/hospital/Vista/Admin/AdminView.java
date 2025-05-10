package com.example.hospital.Vista.Admin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class AdminView {
    public void mostrar() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-view.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Panel Administrador");
        stage.setScene(new Scene(root));
        stage.show();
    }
}