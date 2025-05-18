package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Presentacion;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class PresentacionDao extends Dao<Presentacion> {
    public PresentacionDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Presentacion presentacion) {
        String sql = "INSERT INTO Presentacion (idPresentacion, cantidadUnidad, unidad, descripcion) VALUES (?, ?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, presentacion.getIdPresentacion());
            preparedStatement.setInt(2, presentacion.getCantidadUnidad());
            preparedStatement.setString(3, presentacion.getUnidad());
            preparedStatement.setString(4, presentacion.getDescripcion());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Presentacion WHERE idPresentacion = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Presentacion presentacion) {
        String sql = "UPDATE Presentacion SET cantidadUnidad = ?, unidad = ?, descripcion = ? WHERE idPresentacion = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, presentacion.getCantidadUnidad());
            preparedStatement.setString(2, presentacion.getUnidad());
            preparedStatement.setString(3, presentacion.getDescripcion());
            preparedStatement.setInt(4, presentacion.getIdPresentacion());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Presentacion consultar(String id) {
        String sql = "SELECT * FROM Presentacion WHERE idPresentacion = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Presentacion(
                            resultSet.getInt("idPresentacion"),
                            resultSet.getInt("cantidadUnidad"),
                            resultSet.getString("unidad"),
                            resultSet.getString("descripcion")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Presentacion> listar() {
        ObservableList<Presentacion> presentaciones = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT * FROM Presentacion";
        try (var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Presentacion presentacion = new Presentacion(
                        resultSet.getInt("idPresentacion"),
                        resultSet.getInt("cantidadUnidad"),
                        resultSet.getString("unidad"),
                        resultSet.getString("descripcion")
                );
                presentaciones.add(presentacion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presentaciones;
    }
}
