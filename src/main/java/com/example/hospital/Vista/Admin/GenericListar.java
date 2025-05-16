package com.example.hospital.Vista.Admin;

import com.example.hospital.Controller.Admin.AdminInsController;
import com.example.hospital.Controller.Admin.DoctorController;
import com.example.hospital.Controller.Admin.EnfermeroController;
import com.example.hospital.Controller.Admin.PacienteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenericListar {
    public void mostrar(String fxml,String text) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listar/"+fxml+"-listar.fxml"));
        Parent root = loader.load();
        if (fxml.equals("admin")){
            AdminInsController controller = loader.getController();
            controller.setText(text,fxml);
            controller.inizializar();
        }
        if (fxml.equals("paciente")){
            PacienteController controller = loader.getController();
            controller.setText(text,fxml);
        }
        if (fxml.equals("doctor")){
            DoctorController controller = loader.getController();
            controller.setText(text,fxml);
        }
        if (fxml.equals("enfermero")){
            EnfermeroController controller = loader.getController();
            controller.setText(text,fxml);
        }

        Stage stage = new Stage();
        stage.setTitle(fxml);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
