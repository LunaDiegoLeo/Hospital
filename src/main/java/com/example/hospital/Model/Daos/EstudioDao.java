package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Estudio;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstudioDao extends Dao<Estudio> {
    public EstudioDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Estudio estudio) {
        String sql = "INSERT INTO Estudio (idEstudio, descripcion, costo) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, estudio.getIdEstudio());
            preparedStatement.setString(2, estudio.getDescripcion());
            preparedStatement.setDouble(3, estudio.getCosto());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Estudio WHERE idEstudio = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Estudio estudio) {
        String sql = "UPDATE Estudio SET descripcion = ?, costo = ? WHERE idEstudio = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, estudio.getDescripcion());
            preparedStatement.setDouble(2, estudio.getCosto());
            preparedStatement.setInt(3, estudio.getIdEstudio());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Estudio consultar(String id) {
        String sql = "SELECT " +
                "e.idEstudio, " +
                "e.descripcion, " +
                "e.costo, " +
                "GROUP_CONCAT(h.idLaboratorio SEPARATOR ', ') AS laboratorios " +
                "FROM Estudio e " +
                "LEFT JOIN hace h ON e.idEstudio = h.idEstudio " +
                "WHERE e.idEstudio = ? " +
                "GROUP BY e.idEstudio, e.descripcion, e.costo";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Estudio(
                            resultSet.getInt("idEstudio"),
                            resultSet.getString("descripcion"),
                            resultSet.getDouble("costo"),
                            resultSet.getString("laboratorios") // Ahora es una cadena con todos los laboratorios
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Estudio> listar() {
        ObservableList<Estudio> estudios = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT " +
                "e.idEstudio, " +
                "e.descripcion, " +
                "e.costo, " +
                "GROUP_CONCAT(h.idLaboratorio SEPARATOR ', ') AS laboratorios " +
                "FROM Estudio e " +
                "LEFT JOIN hace h ON e.idEstudio = h.idEstudio " +
                "GROUP BY e.idEstudio, e.descripcion, e.costo";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Estudio estudio = new Estudio(
                        resultSet.getInt("idEstudio"),
                        resultSet.getString("descripcion"),
                        resultSet.getDouble("costo"),
                        resultSet.getString("laboratorios") // Ahora es una cadena con todos los laboratorios
                );
                estudios.add(estudio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estudios;
    }

}
