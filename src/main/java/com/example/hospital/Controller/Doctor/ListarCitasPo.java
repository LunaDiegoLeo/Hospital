package com.example.hospital.Controller.Doctor;

import com.example.hospital.Controller.Paciente.ListarRecetas;
import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Vista.Doctor.DoctorView;
import com.example.hospital.Vista.Paciente.PacienteView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListarCitasPo {

    @FXML
    private Button reg;

    @FXML
    private TableView<ListarCitasPo.ProximasCitas> tablaAdmin;
    @FXML
    private TableColumn<ProximasCitas, String> idad;
    @FXML
    private TableColumn<ProximasCitas, String> hora;
    @FXML
    private TableColumn<ProximasCitas, String> fecha;

    private String nombrePaciente;
    private String idPaciente;

    public void setText(String nombre, String id) {
        this.nombrePaciente = nombre;
        this.idPaciente = id;
        cargarRecetas();
    }


    @FXML
    private void regresar() throws Exception {
        DoctorView view= new DoctorView();
        view.mostrar(nombrePaciente,idPaciente);
        reg.getScene().getWindow().hide();
    }

    public void cargar() {
        idad.setCellValueFactory(new PropertyValueFactory<>("nombrePaciente"));
        hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    private void cargarRecetas() {
        List<ListarCitasPo.ProximasCitas> recetas = new ArrayList<>();

        String sql = """
                SELECT nombre,hora,fecha from citamedica as c inner join
                Paciente as p on p.idpaciente=c.idpaciente where DATE(fecha)=CURRENT_DATE and hora>=(SELECT DATE_FORMAT(NOW( ), "%H:%i" )) and c.idDoctor=? ORDER BY hora
                """;

        try {
            Connection conn = ConexioBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPaciente);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");

                recetas.add(new ProximasCitas(nombre,hora,fecha));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        tablaAdmin.getItems().clear();
        tablaAdmin.getItems().addAll(recetas);
    }


    public class ProximasCitas {
        private String nombrePaciente;
        private String hora;
        private String fecha;

        public ProximasCitas(String nombre, String hora, String fecha) {
            this.nombrePaciente = nombre;
            this.hora = hora;
            this.fecha = fecha;
        }

        public String getNombrePaciente() {
            return nombrePaciente;
        }

        public String getHora() {
            return hora;

        }

        public String getFecha() {
            return fecha;
        }
    }

}
