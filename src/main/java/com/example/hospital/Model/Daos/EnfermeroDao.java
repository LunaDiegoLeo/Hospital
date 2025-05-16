package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Enfermero;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EnfermeroDao extends Dao<Enfermero> {
    public EnfermeroDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Enfermero enfermero) {
        String sql = "INSERT INTO Enfermero (idEnfermero, nombre, curp, cedula, rfc, sueldoPorDia, idTurno, idDepartamento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, enfermero.getIdEnfermero());
            stmt.setString(2, enfermero.getNombre());
            stmt.setString(3, enfermero.getCurp());
            stmt.setString(4, enfermero.getCedula());
            stmt.setString(5, enfermero.getRfc());
            stmt.setDouble(6, enfermero.getSueldoPorDia());
            stmt.setInt(7, enfermero.getIdTurno());
            stmt.setString(8, enfermero.getIdDepartamento());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Enfermero WHERE idEnfermero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modificar(Enfermero enfermero) {
        String sql = "UPDATE Enfermero SET  nombre = ?, curp = ?, cedula = ?, rfc = ?, sueldoPorDia = ?, idTurno = ?, idDepartamento = ? WHERE idEnfermero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, enfermero.getNombre());
            stmt.setString(2, enfermero.getCurp());
            stmt.setString(3, enfermero.getCedula());
            stmt.setString(4, enfermero.getRfc());
            stmt.setDouble(5, enfermero.getSueldoPorDia());
            stmt.setInt(6, enfermero.getIdTurno());
            stmt.setString(7, enfermero.getIdDepartamento());
            stmt.setString(8, enfermero.getIdEnfermero());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Enfermero consultar(String id) {
        String sql = "SELECT * FROM Enfermero WHERE idEnfermero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Enfermero(
                        resultSet.getString("idEnfermero"),
                        resultSet.getString("nombre"),
                        resultSet.getString("curp"),
                        resultSet.getString("cedula"),
                        resultSet.getString("rfc"),
                        resultSet.getDouble("sueldoPorDia"),
                        resultSet.getInt("idTurno"),
                        resultSet.getString("idDepartamento")
                );
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public ObservableList<Enfermero> listar() {
        String sql = "SELECT * FROM Enfermero";
        ObservableList<Enfermero> enfermeros = javafx.collections.FXCollections.observableArrayList();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             var resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Enfermero enfermero = new Enfermero(
                        resultSet.getString("idEnfermero"),
                        resultSet.getString("nombre"),
                        resultSet.getString("curp"),
                        resultSet.getString("cedula"),
                        resultSet.getString("rfc"),
                        resultSet.getDouble("sueldoPorDia"),
                        resultSet.getInt("idTurno"),
                        resultSet.getString("idDepartamento")
                );
                enfermeros.add(enfermero);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return enfermeros;
    }
}
