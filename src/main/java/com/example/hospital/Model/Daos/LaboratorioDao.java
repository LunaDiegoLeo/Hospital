package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Laboratorio;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.util.ArrayList;

public class LaboratorioDao extends Dao<Laboratorio> {
    public LaboratorioDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Laboratorio laboratorio) {
        String sql = "INSERT INTO Laboratorio (idLaboratorio, descripcion, piso) VALUES (?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, laboratorio.getIdLaboratorio());
            preparedStatement.setString(2, laboratorio.getDescripcion());
            preparedStatement.setInt(3, laboratorio.getPiso());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Laboratorio WHERE idLaboratorio = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modificar(Laboratorio laboratorio) {
        String sql = "UPDATE Laboratorio SET descripcion = ?, piso = ? WHERE idLaboratorio = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, laboratorio.getDescripcion());
            preparedStatement.setInt(2, laboratorio.getPiso());
            preparedStatement.setString(3, laboratorio.getIdLaboratorio());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Laboratorio consultar(String id) {
        String sql = "SELECT * FROM Laboratorio WHERE idLaboratorio = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Laboratorio(
                            resultSet.getString("idLaboratorio"),
                            resultSet.getString("descripcion"),
                            resultSet.getInt("piso")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Laboratorio> listar() {
        String sql = "SELECT * FROM Laboratorio";
        try (var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {

            var laboratorios = new ArrayList<Laboratorio>();
            while (resultSet.next()) {
                laboratorios.add(new Laboratorio(
                        resultSet.getString("idLaboratorio"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("piso")
                ));
            }
            return javafx.collections.FXCollections.observableArrayList(laboratorios);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return javafx.collections.FXCollections.observableArrayList();
    }
}
