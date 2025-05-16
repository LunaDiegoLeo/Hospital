package com.example.hospital.Vista.Admin;

import com.example.hospital.Controller.Admin.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenericAct {
    public void mostrar(String fxml,String text) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modificar/"+fxml+"-modificar.fxml"));
        Parent root = loader.load();
        if (fxml.equals("admin")){
            AdminInsController controller = loader.getController();
            controller.setText(text,fxml);
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
        if (fxml.equals("compania")){
            CompaniaController controller = loader.getController();
            controller.setText(text,fxml);
        }
        if (fxml.equals("laboratorio")){
            LaboratorioController controller = loader.getController();
            controller.setText(text,fxml);
        }
        if (fxml.equals("estudio")){
            EstudioController controller = loader.getController();
            controller.setText(text,fxml);
        }

        Stage stage = new Stage();
        stage.setTitle(fxml);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
