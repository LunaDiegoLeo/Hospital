package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Hace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HaceDao extends Dao<Hace> {
    public HaceDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Hace hace) {
        String sql = "INSERT INTO hace (idLaboratorio, idEstudio) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, hace.getIdLaboratorio());
            preparedStatement.setInt(2, hace.getIdEstudio());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM hace WHERE idEstudio = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Hace hace) {
        return false;
    }

    @Override
    public Hace consultar(String id) {
        return null;
    }

    @Override
    public ObservableList<Hace> listar() {
        return null;
    }

    public ObservableList<String> listarLaboratoriosPorEstudio(int idEstudio) {
        ObservableList<String> laboratorios = FXCollections.observableArrayList();
        String sql = "SELECT idLaboratorio FROM hace WHERE idEstudio = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idEstudio);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    laboratorios.add(resultSet.getString("idLaboratorio"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return laboratorios;
    }
}
