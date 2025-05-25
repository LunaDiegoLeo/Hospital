package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.CitaMedica;
import javafx.collections.ObservableList;

import java.sql.Connection;

import com.example.hospital.Model.Tablas.CitaMedica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.hospital.Model.ConexioBD.connection;

public class CitaMedicaDao extends Dao<CitaMedica> {

    public CitaMedicaDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(CitaMedica citaMedica) {
        String queryDoctor = "SELECT iddoctor FROM doctor WHERE iddepartamento = ?";
        String queryInsert = "INSERT INTO citamedica (idcita, hora, fecha, idpaciente, iddepartamento, costo, iddoctor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement psDoctor = connection.prepareStatement(queryDoctor);
            psDoctor.setString(1, citaMedica.getIdDepartamento());
            ResultSet rs = psDoctor.executeQuery();

            // Elegir un doctor al azar
            ObservableList<String> doctores = FXCollections.observableArrayList();
            while (rs.next()) {
                doctores.add(rs.getString("iddoctor"));
            }
            if (doctores.isEmpty()) return false;

            String doctorAsignado = doctores.get(new Random().nextInt(doctores.size()));
            citaMedica.setIdDoctor(doctorAsignado);

            PreparedStatement psInsert = connection.prepareStatement(queryInsert);
            psInsert.setString(1, citaMedica.getIdCita());
            psInsert.setString(2, citaMedica.getHora().toString());
            psInsert.setString(3, citaMedica.getFecha().toString());
            psInsert.setString(4, citaMedica.getIdPaciente());
            psInsert.setString(5, citaMedica.getIdDepartamento());
            psInsert.setDouble(6, citaMedica.getCosto());
            psInsert.setString(7, citaMedica.getIdDoctor());

            return psInsert.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String query = "DELETE FROM citamedica WHERE idcita = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(CitaMedica citaMedica) {
        String query = "UPDATE citamedica SET fecha = ?, hora = ? WHERE idcita = ?";

        try {
            LocalDate hoy = LocalDate.now();
            LocalDate fechaCita = LocalDate.parse(citaMedica.getFecha().toString());

            if (fechaCita.isAfter(hoy.plusDays(1))) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, citaMedica.getFecha().toString());
                ps.setString(2, citaMedica.getHora().toString());
                ps.setString(3, citaMedica.getIdCita());
                return ps.executeUpdate() > 0;
            } else {
                System.out.println("No se puede modificar. Debe haber más de un día de anticipación.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CitaMedica consultar(String id) {
        String query = "SELECT * FROM citamedica WHERE idcita = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CitaMedica(
                        rs.getString("idcita"),
                        rs.getString("hora"),
                        rs.getString("fecha"),
                        rs.getString("idpaciente"),
                        rs.getString("iddepartamento"),
                        rs.getDouble("costo"),
                        rs.getString("iddoctor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<CitaMedica> listar() {
        ObservableList<CitaMedica> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM citamedica";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
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

    // Listar citas por ID de paciente
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

    public List<LocalTime> obtenerHorasOcupadas(LocalDate fecha, String idDepartamento) {
        List<LocalTime> horasOcupadas = new ArrayList<>();
        String sql = "SELECT hora FROM citamedica WHERE fecha = ? AND iddepartamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setString(2, idDepartamento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                horasOcupadas.add(rs.getTime("hora").toLocalTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horasOcupadas;
    }

}

