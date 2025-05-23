package com.example.hospital.Vista.Admin;

import com.example.hospital.Controller.Admin.AdminController;
import com.example.hospital.Controller.Admin.GenericController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenericView {
    public void mostrar(String fxml,String text) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        String[] a=fxml.split("-");
        Object objController = loader.getController();
        if (objController instanceof GenericController controller) {
            controller.setText(text, a[0]);
        }
        Stage stage = new Stage();
        stage.setTitle(a[0]);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
