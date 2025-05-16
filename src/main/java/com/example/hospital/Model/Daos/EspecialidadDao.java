package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Especialidad;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class EspecialidadDao extends Dao<Especialidad> {
    public EspecialidadDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Especialidad especialidad) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(Especialidad especialidad) {
        return false;
    }

    @Override
    public Especialidad consultar(String id) {

        String sql = "SELECT * FROM especialidad WHERE idEspecialidad = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Especialidad(
                            resultSet.getInt("idEspecialidad"),
                            resultSet.getString("descripcion") // Adjust these fields per your table schema
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Especialidad> listar() {
        ObservableList<Especialidad> especialidades = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT * FROM especialidad";

        try (var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Especialidad especialidad = new Especialidad(
                        resultSet.getInt("idEspecialidad"),
                        resultSet.getString("descripcion") // Adjust these fields per your table schema
                );
                especialidades.add(especialidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return especialidades;
    }
}
