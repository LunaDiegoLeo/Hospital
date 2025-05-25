package com.example.hospital.Controller.Paciente;

import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.Daos.DoctorDao;
import com.example.hospital.Model.Tablas.CitaMedica;
import com.example.hospital.Model.Tablas.Departamento;
import com.example.hospital.Vista.Paciente.CitasView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.hospital.Model.ConexioBD.connection;

public class ListarCitas {
    private final Dao<CitaMedica> citaMedicaDao = (Dao<CitaMedica>) DaoFactory.CITA.crear(connection);
    private final Dao<Departamento> departamentoDao = (Dao<Departamento>) DaoFactory.DEPARTAMENTO.crear(connection);
    private DoctorDao doctorDao = (DoctorDao) DaoFactory.DOCTOR.crear(connection);

    @FXML
    private Button reg;

    private String nombre;
    private String idPaciente;


    public void setText(String text, String id) {
        nombre = text;
        idPaciente = id;
    }

    @FXML
    private javafx.scene.control.TableView<CitaMedica> tablaAdmin;
    @FXML
    private javafx.scene.control.TableColumn<CitaMedica, String> idad;
    @FXML
    private javafx.scene.control.TableColumn<CitaMedica, String> hora;
    @FXML
    private javafx.scene.control.TableColumn<CitaMedica, String> fecha;
    @FXML
    private javafx.scene.control.TableColumn<CitaMedica, String> depa;
    @FXML
    private javafx.scene.control.TableColumn<CitaMedica, String> piso;
    @FXML
    private javafx.scene.control.TableColumn<CitaMedica, String> doctor;

    @FXML
    private void initialize() {

    }

    public void cargar(){
        // Configurar columnas con las propiedades adecuadas
        idad.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIdCita()));
        hora.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getHora().toString()));
        fecha.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFecha().toString()));

        // Departamento - nombre
        depa.setCellValueFactory(data -> {
            Departamento departamento = departamentoDao.consultar(data.getValue().getIdDepartamento());
            return new javafx.beans.property.SimpleStringProperty(departamento != null ? departamento.getDescripcion() : "No encontrado");
        });

        // Departamento - piso
        piso.setCellValueFactory(data -> {
            Departamento departamento = departamentoDao.consultar(data.getValue().getIdDepartamento());
            return new javafx.beans.property.SimpleStringProperty(departamento != null ? String.valueOf(departamento.getPiso()) : "No encontrado");
        });

        // Doctor - nombre
        doctor.setCellValueFactory(data -> {
            com.example.hospital.Model.Tablas.Doctor d = doctorDao.consultar(data.getValue().getIdDoctor());
            return new javafx.beans.property.SimpleStringProperty(d != null ? d.getNombre() : "No encontrado");
        });

        // Cargar solo citas desde hoy en adelante
        java.time.LocalDate hoy = java.time.LocalDate.now();
        tablaAdmin.getItems().clear();
        tablaAdmin.getItems().addAll(
                listarPorIdPaciente(idPaciente).stream()
                        .filter(c -> {
                            java.time.LocalDate fechaCita = java.time.LocalDate.parse(c.getFecha().toString());
                            return !fechaCita.isBefore(hoy);
                        })
                        .toList()
        );
    }


    @FXML
    private void regresar() throws IOException {
        CitasView v = new CitasView();
        v.mostrarCitas(nombre, idPaciente);
        reg.getScene().getWindow().hide();
    }


    public ObservableList<CitaMedica> listarPorIdPaciente(String idPaciente) {
        ObservableList<CitaMedica> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM citamedica WHERE idpaciente = ? ORDER BY fecha,hora";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, idPaciente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CitaMedica cita = new CitaMedica(
                        rs.getString("idcita"),
                        rs.getString("hora"),
                        rs.getString("fecha"),
                        rs.getString("idpaciente"),
                        rs.getString("iddepartamento"),
                        rs.getDouble("costo"),
                        rs.getString("iddoctor")
                );
                lista.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
