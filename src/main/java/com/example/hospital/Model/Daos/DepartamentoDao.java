package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Departamento;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class DepartamentoDao extends Dao<Departamento> {
    public DepartamentoDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Departamento departamento) {
        String sql = "INSERT INTO Departamento (IDDepartamento, Descripcion, Piso) VALUES (?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, departamento.getIdDepartamento());
            preparedStatement.setString(2, departamento.getDescripcion());
            preparedStatement.setInt(3, departamento.getPiso());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Departamento WHERE IDDepartamento = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Departamento departamento) {
        String sql = "UPDATE Departamento SET Descripcion = ?, Piso = ? WHERE IDDepartamento = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, departamento.getDescripcion());
            preparedStatement.setInt(2, departamento.getPiso());
            preparedStatement.setString(3, departamento.getIdDepartamento());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Departamento consultar(String id) {
        Departamento departamento = null;
        String sql = "SELECT IDDepartamento, Descripcion, Piso FROM Departamento WHERE IDDepartamento = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String descripcion = resultSet.getString("Descripcion");
                    int piso = resultSet.getInt("Piso");
                    departamento = new Departamento(id, descripcion, piso);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departamento;
    }

    @Override
    public ObservableList<Departamento> listar() {
        ObservableList<Departamento> departamentos = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT IDDepartamento, Descripcion, Piso FROM Departamento";
        try (var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("IDDepartamento");
                String descripcion = resultSet.getString("Descripcion");
                int piso = resultSet.getInt("Piso");
                departamentos.add(new Departamento(id, descripcion, piso));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departamentos;
    }
}
