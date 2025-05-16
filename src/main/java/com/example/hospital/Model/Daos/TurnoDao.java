package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Turno;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class TurnoDao extends Dao<Turno> {
    public TurnoDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Turno turno) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(Turno turno) {
        return false;
    }

    @Override
    public Turno consultar(String id) {
        String query = "SELECT IDTurno, HoraInicio, HoraFin, Descripcion FROM Turno WHERE IDTurno = ?";
        try (java.sql.PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Turno(
                            rs.getInt("IDTurno"),
                            rs.getString("HoraInicio"),
                            rs.getString("HoraFin"),
                            rs.getString("Descripcion")
                    );
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Turno> listar() {
        ObservableList<Turno> turnos = javafx.collections.FXCollections.observableArrayList();
        String query = "SELECT IDTurno, HoraInicio, HoraFin, Descripcion FROM Turno";

        try (java.sql.Statement stmt = connection.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Turno turno = new Turno(
                        rs.getInt("IDTurno"),
                        rs.getString("HoraInicio"),
                        rs.getString("HoraFin"),
                        rs.getString("Descripcion")
                );
                turnos.add(turno);
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        return turnos;
    }
}
