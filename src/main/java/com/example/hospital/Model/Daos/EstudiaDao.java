package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Estudia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudiaDao extends Dao<Estudia> {

    public EstudiaDao(Connection connection) {
        super(connection);
    }

    public boolean insertar(Estudia estudia) {
        String sql = "INSERT INTO estudia (IDDoctor, IDPaciente, IDEstudio) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estudia.getIdDoctor());
            stmt.setString(2, estudia.getIdPaciente());
            stmt.setString(3, estudia.getIdEstudio());
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Estudia consultar(String id) {
        String sql = "SELECT * FROM estudia WHERE IDDoctor = ? AND IDPaciente = ? AND IDEstudio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Estudia(
                            rs.getString("IDDoctor"),
                            rs.getString("IDPaciente"),
                            rs.getString("IDEstudio")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Estudia> listar() {
        ObservableList<Estudia> lista= FXCollections.observableArrayList();
        String sql = "SELECT * FROM estudia";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String idDoctor = rs.getString("IDDoctor");
                String idPaciente = rs.getString("IDPaciente");
                String idEstudio = rs.getString("IDEstudio");
                Estudia estudia = new Estudia(idDoctor, idPaciente, idEstudio);
                lista.add(estudia);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean eliminar(String id) {
        String sql = "DELETE FROM estudia WHERE IDDoctor = ? AND IDPaciente = ? AND IDEstudio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modificar(Estudia estudia) {
        String sql = "UPDATE estudia SET IDDoctor = ?, IDPaciente = ?, IDEstudio = ? WHERE IDDoctor = ? AND IDPaciente = ? AND IDEstudio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estudia.getIdDoctor());
            stmt.setString(2, estudia.getIdPaciente());
            stmt.setString(3, estudia.getIdEstudio());
            stmt.setString(4, estudia.getIdDoctor());
            stmt.setString(5, estudia.getIdPaciente());
            stmt.setString(6, estudia.getIdEstudio());
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
