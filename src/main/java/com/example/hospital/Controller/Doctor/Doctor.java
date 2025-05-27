package com.example.hospital.Controller.Doctor;


import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.Tablas.Paciente;
import com.example.hospital.Vista.Doctor.CitasProxView;
import com.example.hospital.Vista.Doctor.EstudiaView;
import com.example.hospital.Vista.Doctor.Intervenir;
import com.example.hospital.Vista.Doctor.RecetarView;
import com.example.hospital.Vista.Paciente.CitasView;
import com.example.hospital.Vista.Paciente.EstudiosView;
import com.example.hospital.Vista.Paciente.InterView;
import com.example.hospital.Vista.Paciente.RecetaView;
import com.example.hospital.Vista.login;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.example.hospital.Model.ConexioBD.connection;

public class Doctor {
    @FXML
    private Label label;
    @FXML
    private Button cerrar;
    @FXML
    private Button pac;
    @FXML
    private Button doc;
    @FXML
    private Button enfe;
    private String paciente;
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
        inicializarBotones();
    }

    @FXML
    protected void irCitas() throws Exception {
        CitasProxView citasProxView = new CitasProxView();
        citasProxView.mostrar(usua.getText(), id);
        cerrar.getScene().getWindow().hide();
    }
    @FXML
    protected void recetas() throws Exception {
        RecetarView view= new RecetarView();
        view.mostrar(usua.getText(),id,paciente);
        cerrar.getScene().getWindow().hide();
    }

    @FXML
    protected void intervenciones() throws Exception {
        Intervenir view = new Intervenir();
        view.mostrar(usua.getText(),id,paciente);
        cerrar.getScene().getWindow().hide();
    }

    @FXML
    protected void estudios() throws Exception {
        EstudiaView view= new EstudiaView();
        view.mostrar(usua.getText(),id,paciente);
        cerrar.getScene().getWindow().hide();
    }

    private void inicializarBotones() {
        doc.setVisible(false);
        pac.setVisible(false);
        enfe.setVisible(false);


        paciente = obtenerPacienteProximaCita();
        if (paciente != null) {
            doc.setVisible(true);
            enfe.setVisible(true);
            pac.setVisible(true);
            Dao<Paciente> pacienteDaoDao = (Dao<Paciente>) DaoFactory.PACIENTE.crear(connection);
            Paciente paciente1= pacienteDaoDao.consultar(paciente);
            label.setText("Esta atendiendo al paciente: "+paciente1.getNombre());
        }
    }


    private String obtenerPacienteProximaCita() {

        String idPacienteEncontrado = null;

        try {
            String sql = "SELECT IDPaciente, Fecha, Hora FROM citamedica WHERE IDDoctor=?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String fechaStr = rs.getString("Fecha");
                    String horaStr = rs.getString("Hora");
                    String idPaciente = rs.getString("IDPaciente");

                    LocalDate fecha = LocalDate.parse(fechaStr);
                    LocalTime hora = LocalTime.parse(horaStr);
                    LocalDateTime citaDateTime = LocalDateTime.of(fecha, hora);

                    LocalDateTime finCita = citaDateTime.plusMinutes(30);
                    LocalDateTime ahora = LocalDateTime.now();

                    if (!ahora.isBefore(citaDateTime) && ahora.isBefore(finCita)) {
                        idPacienteEncontrado = idPaciente;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return idPacienteEncontrado;
    }

}
