package com.example.hospital.Controller.Paciente;

import com.example.hospital.Vista.Paciente.AgendarView;
import com.example.hospital.Vista.Paciente.ListarView;
import com.example.hospital.Vista.Paciente.PacienteView;
import com.example.hospital.Vista.Paciente.ReagendarView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CitasController {

    private String id;
    private String text;

    @FXML
    private void agendar() throws IOException {
        AgendarView ag = new AgendarView();
        ag.mostrar(text,id);
        reg.getScene().getWindow().hide();
    }

    @FXML
    private void modificar() throws IOException {
        ReagendarView ag = new ReagendarView();
        ag.mostrar(text,id);
        reg.getScene().getWindow().hide();
    }

    public void setText(String text,String id){
        this.text = text;
        this.id = id;
    }

    @FXML
    private void vercitas() throws IOException {
        ListarView v = new ListarView();
        v.mostrar(text,id);
        reg.getScene().getWindow().hide();
    }

    @FXML
    private Button reg;
    @FXML
    private void regresar() throws Exception {
        PacienteView v = new PacienteView();
        v.mostrar(text,id);
        reg.getScene().getWindow().hide();
    }
}
