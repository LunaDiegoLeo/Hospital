package com.example.hospital.Controller.Paciente;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Vista.Paciente.CitasView;
import com.example.hospital.Vista.Paciente.PacienteView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListarRecetas {

    @FXML
    private Button regresarBtn;
    @FXML
    private TableView<RecetaVista> tablaRecetas;
    @FXML
    private TableColumn<RecetaVista, String> colDoctor;
    @FXML
    private TableColumn<RecetaVista, String> colMedicamento;
    @FXML
    private TableColumn<RecetaVista, String> colDosis;
    @FXML
    private TableColumn<RecetaVista, String> colFecha;
    @FXML
    private TableColumn<RecetaVista, String> colHora;

    private String nombrePaciente;
    private String idPaciente;

    public void setText(String nombre, String id) {
        this.nombrePaciente = nombre;
        this.idPaciente = id;
        cargarRecetas();
    }

    @FXML
    private void initialize() {

    }

    public void cargar(){
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        colMedicamento.setCellValueFactory(new PropertyValueFactory<>("medicamento"));
        colDosis.setCellValueFactory(new PropertyValueFactory<>("dosis"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    }

    private void cargarRecetas() {
        List<RecetaVista> recetas = new ArrayList<>();

        String sql = """
                SELECT r.dosis, r.fecha, r.hora, m.nombre As nombre_m, d.nombre AS doctor_nombre
                FROM receta r
                JOIN doctor d ON r.iddoctor = d.iddoctor
                JOIN medicamento m ON r.idmedicamento = m.idmedicamento
                WHERE r.idpaciente = ?
                  AND DATE(r.fecha) + INTERVAL 10 DAY >= CURRENT_DATE
                ORDER BY r.fecha ASC
                """;

        try {
            Connection conn = ConexioBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String doctor = rs.getString("doctor_nombre");
                String medicamento = rs.getString("nombre_m");
                String dosis = rs.getString("dosis");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");

                recetas.add(new RecetaVista(doctor, medicamento, dosis, fecha, hora));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        tablaRecetas.getItems().clear();
        tablaRecetas.getItems().addAll(recetas);
    }

    @FXML
    private void regresar() throws Exception {
        PacienteView view= new PacienteView();
        view.mostrar(nombrePaciente,idPaciente);
        regresarBtn.getScene().getWindow().hide();
    }

    // Clase interna para la vista
    public class RecetaVista {
        private final String doctor;
        private final String medicamento;
        private final String dosis;
        private final String fecha;
        private final String hora;

        public RecetaVista(String doctor, String medicamento, String dosis, String fecha, String hora) {
            this.doctor = doctor;
            this.medicamento = medicamento;
            this.dosis = dosis;
            this.fecha = fecha;
            this.hora = hora;
        }

        public String getDoctor() { return doctor; }
        public String getMedicamento() { return medicamento; }
        public String getDosis() { return dosis; }
        public String getFecha() { return fecha; }
        public String getHora() { return hora; }
    }
}
