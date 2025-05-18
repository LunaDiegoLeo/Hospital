package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.TipoMedicamento;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class TipoMedicamentoDao extends Dao<TipoMedicamento> {
    public TipoMedicamentoDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(TipoMedicamento tipoMedicamento) {
        String sql = "INSERT INTO TipoMedicamento (idTipoM, descripcion) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tipoMedicamento.getIdTipoM());
            preparedStatement.setString(2, tipoMedicamento.getDescripcion());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM TipoMedicamento WHERE idTipoM = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(TipoMedicamento tipoMedicamento) {
        String sql = "UPDATE TipoMedicamento SET descripcion = ? WHERE idTipoM = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tipoMedicamento.getDescripcion());
            preparedStatement.setInt(2, tipoMedicamento.getIdTipoM());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TipoMedicamento consultar(String id) {
        String sql = "SELECT idTipoM, descripcion FROM TipoMedicamento WHERE idTipoM = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int idTipoM = resultSet.getInt("idTipoM");
                    String descripcion = resultSet.getString("descripcion");
                    return new TipoMedicamento(idTipoM, descripcion);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<TipoMedicamento> listar() {
        ObservableList<TipoMedicamento> tipoMedicamentos = javafx.collections.FXCollections.observableArrayList();
        String query = "SELECT idTipoM, descripcion FROM TipoMedicamento";

        try (var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("idTipoM");
                String descripcion = resultSet.getString("descripcion");

                tipoMedicamentos.add(new TipoMedicamento(id, descripcion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipoMedicamentos;
    }
}
