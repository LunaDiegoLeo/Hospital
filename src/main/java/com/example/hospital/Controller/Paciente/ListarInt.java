package com.example.hospital.Controller.Paciente;

import com.example.hospital.Model.ConexioBD;
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

public class ListarInt {


    @FXML
    private Button regresarBtn;
    @FXML
    private TableView<ListarInt.IntervencionesVista> tablaRecetas;
    @FXML
    private TableColumn<ListarInt.IntervencionesVista, String> colDoctor;
    @FXML
    private TableColumn<ListarInt.IntervencionesVista, String> colIntervencion;
    @FXML
    private TableColumn<ListarInt.IntervencionesVista, String> colCosto;


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
        colIntervencion.setCellValueFactory(new PropertyValueFactory<>("intervencion"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

    }

    private void cargarRecetas() {
        List<ListarInt.IntervencionesVista> recetas = new ArrayList<>();

        String sql = """
                SELECT d.nombre AS doctor_nombre, i.nombre as inter_nombre,costo
                FROM seinterviene s
                JOIN doctor d ON s.iddoctor = d.iddoctor
                JOIN intervencion i ON s.idintervencion = i.idintervencion
                WHERE s.idpaciente = ?
                """;

        try {
            Connection conn = ConexioBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String doctor = rs.getString("doctor_nombre");
                String inter = rs.getString("inter_nombre");
                String costo = rs.getString("costo");


                recetas.add(new ListarInt.IntervencionesVista(doctor, inter, costo));
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
    public class IntervencionesVista {
        private final String doctor;
        private final String intervencion;
        private final String costo;

        public IntervencionesVista(String doctor, String intervencion, String costo) {
            this.doctor = doctor;
            this.intervencion = intervencion;
            this.costo = costo;
        }
        public String getIntervencion() {
            return intervencion;
        }

        public String getDoctor() { return doctor; }

        public String getCosto() {
            return costo;
        }
    }
}
