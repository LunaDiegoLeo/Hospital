package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Intervencion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class IntervencionDao extends Dao<Intervencion> {
    public IntervencionDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Intervencion intervencion) {
        String sql = "INSERT INTO Intervencion (idIntervencion, nombre, descripcion, idDepartamento, numero) VALUES (?, ?, ?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, intervencion.getIdIntervencion());
            preparedStatement.setString(2, intervencion.getNombre());
            preparedStatement.setString(3, intervencion.getDescripcion());
            preparedStatement.setString(4, intervencion.getIdDepartamento());
            preparedStatement.setString(5, intervencion.getNumero());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Intervencion WHERE idIntervencion = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modificar(Intervencion intervencion) {
        String sql = "UPDATE Intervencion SET nombre = ?, descripcion = ?, idDepartamento = ?, numero = ? WHERE idIntervencion = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, intervencion.getNombre());
            preparedStatement.setString(2, intervencion.getDescripcion());
            preparedStatement.setString(3, intervencion.getIdDepartamento());
            preparedStatement.setString(4, intervencion.getNumero());
            preparedStatement.setString(5, intervencion.getIdIntervencion());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Intervencion consultar(String id) {
        String sql = "SELECT * FROM Intervencion WHERE idIntervencion = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new Intervencion(
                            rs.getString("idIntervencion"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("idDepartamento"),
                            rs.getString("numero")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public ObservableList<Intervencion> listar() {
        ObservableList<Intervencion> intervenciones = FXCollections.observableArrayList();
        String sql = "SELECT i.idIntervencion, i.nombre, i.descripcion, i.idDepartamento, i.numero, ts.descripcion AS tipoSalaDescripcion " +
                "FROM Intervencion i " +
                "INNER JOIN Sala s ON i.idDepartamento = s.idDepartamento AND i.numero = s.numero " +
                "INNER JOIN TipoSala ts ON s.TipoSala = ts.TipoSala ORDER BY i.nombre";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Intervencion intervencion = new Intervencion(
                        rs.getString("idIntervencion"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("idDepartamento"),
                        rs.getString("idDepartamento")+" - "+rs.getString("numero") + " - " + rs.getString("tipoSalaDescripcion")
                );
                intervenciones.add(intervencion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intervenciones;
    }

    public String generarNuevoIdIntervencion() {
        String nuevoId = "INT00001";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT idIntervencion FROM intervencion ORDER BY idIntervencion DESC LIMIT 1")) {
            if (rs.next()) {
                String ultimoId = rs.getString("idIntervencion");
                int num = Integer.parseInt(ultimoId.substring(3)) + 1;
                nuevoId = String.format("INT%05d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevoId;
    }

}
