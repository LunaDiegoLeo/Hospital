package com.example.hospital.Controller.Paciente;

import com.example.hospital.Vista.Paciente.CitasView;
import com.example.hospital.Vista.Paciente.EstudiosView;
import com.example.hospital.Vista.Paciente.InterView;
import com.example.hospital.Vista.Paciente.RecetaView;
import com.example.hospital.Vista.login;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class PacienteController {

    @FXML
    private Button cerrar;
    @FXML
    private Label usua;
    private String id;
    @FXML
    protected void cerrar(){
        try{
            login log= new login();
            log.start(new javafx.stage.Stage());
            cerrar.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setText(String text,String id){
        usua.setText(text);
        this.id=id;
    }

    @FXML
    protected void irCitas() throws IOException {
        CitasView view = new CitasView();
        view.mostrarCitas(usua.getText(),id);
        cerrar.getScene().getWindow().hide();
    }
    @FXML
    protected void recetas() throws IOException {
        RecetaView view = new RecetaView();
        view.mostrarCitas(usua.getText(), id);
        cerrar.getScene().getWindow().hide();
    }

    @FXML
    protected void intervenciones() throws IOException {
        InterView view = new InterView();
        view.mostrarCitas(usua.getText(), id);
        cerrar.getScene().getWindow().hide();
    }

    @FXML
    protected void estudios() throws IOException {
        EstudiosView view = new EstudiosView();
        view.mostrarCitas(usua.getText(), id);
        cerrar.getScene().getWindow().hide();
    }
}
