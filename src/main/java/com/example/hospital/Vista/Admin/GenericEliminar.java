package com.example.hospital.Vista.Admin;

import com.example.hospital.Controller.Admin.AdminInsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenericEliminar {
    public void mostrar(String fxml,String text) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("eliminar/"+fxml+"-eliminar.fxml"));
        Parent root = loader.load();
        if (fxml.equals("admin")){
            AdminInsController controller = loader.getController();
            controller.setText(text,fxml);
        }

        Stage stage = new Stage();
        stage.setTitle(fxml);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
