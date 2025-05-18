package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.SePresenta;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SePresentaDao extends Dao<SePresenta> {
    public SePresentaDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(SePresenta sePresenta) {
        String sql = "INSERT INTO SePresenta (idPresentacion, idTipoM, idMedicamento, cantidad, costo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sePresenta.getIdPresentacion());
            stmt.setInt(2, sePresenta.getIdTipoM());
            stmt.setString(3, sePresenta.getIdMedicamento());
            stmt.setInt(4, sePresenta.getCantidad());
            stmt.setDouble(5, sePresenta.getCosto());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM SePresenta WHERE idPresentacion = ? AND idTipoM = ? AND idMedicamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String[] keys = id.split("-");
            stmt.setInt(1, Integer.parseInt(keys[0]));
            stmt.setInt(2, Integer.parseInt(keys[1]));
            stmt.setString(3, keys[2]);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(SePresenta sePresenta) {
        String sql = "UPDATE SePresenta SET cantidad = ?, costo = ? WHERE idPresentacion = ? AND idTipoM = ? AND idMedicamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sePresenta.getCantidad());
            stmt.setDouble(2, sePresenta.getCosto());
            stmt.setInt(3, sePresenta.getIdPresentacion());
            stmt.setInt(4, sePresenta.getIdTipoM());
            stmt.setString(5, sePresenta.getIdMedicamento());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SePresenta consultar(String id) {
        String sql = "SELECT idPresentacion, idTipoM, idMedicamento, cantidad, costo FROM SePresenta WHERE idPresentacion = ? AND idTipoM = ? AND idMedicamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String[] keys = id.split("-");
            stmt.setInt(1, Integer.parseInt(keys[0]));
            stmt.setInt(2, Integer.parseInt(keys[1]));
            stmt.setString(3, keys[2]);
            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new SePresenta(
                            resultSet.getInt("idPresentacion"),
                            resultSet.getInt("idTipoM"),
                            resultSet.getString("idMedicamento"),
                            resultSet.getInt("cantidad"),
                            resultSet.getDouble("costo")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ObservableList<SePresenta> listar() {
        ObservableList<SePresenta> lista = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT idPresentacion, idTipoM, idMedicamento, cantidad, costo FROM SePresenta";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (var resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    lista.add(new SePresenta(
                            resultSet.getInt("idPresentacion"),
                            resultSet.getInt("idTipoM"),
                            resultSet.getString("idMedicamento"),
                            resultSet.getInt("cantidad"),
                            resultSet.getDouble("costo")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
