package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Medicamento;
import javafx.collections.ObservableList;

import java.sql.*;

import static com.example.hospital.Model.ConexioBD.connection;

public class MedicamentoDao extends Dao<Medicamento> {
    public MedicamentoDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Medicamento medicamento) {
        String sql = "INSERT INTO Medicamento (idMedicamento, nombre, descripcion) VALUES (?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, medicamento.getIdMedicamento());
            preparedStatement.setString(2, medicamento.getNombre());
            preparedStatement.setString(3, medicamento.getDescripcion());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Medicamento WHERE idMedicamento = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Medicamento medicamento) {
        String sql = "UPDATE Medicamento SET nombre = ?, descripcion = ? WHERE idMedicamento = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, medicamento.getNombre());
            preparedStatement.setString(2, medicamento.getDescripcion());
            preparedStatement.setString(3, medicamento.getIdMedicamento());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Medicamento consultar(String id) {
        String sql = "SELECT idMedicamento, nombre, descripcion FROM Medicamento WHERE idMedicamento = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Medicamento(
                            resultSet.getString("idMedicamento"),
                            resultSet.getString("nombre"),
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
    public ObservableList<Medicamento> listar() {
        ObservableList<Medicamento> medicamentos = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT idMedicamento, nombre, descripcion FROM Medicamento order by nombre";
        try (var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                medicamentos.add(new Medicamento(
                        resultSet.getString("idMedicamento"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicamentos;
    }

    public String obtenerUltimoId() {
        String ultimoId = null;
        String sql = "SELECT idMedicamento FROM medicamento ORDER BY idMedicamento DESC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                ultimoId = rs.getString("idMedicamento");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ultimoId;
    }

    public Medicamento buscarPorNombre(String nombre) {
        Medicamento medicamento = null;
        String sql = "SELECT * FROM medicamento WHERE nombre = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    medicamento = new Medicamento(
                            rs.getString("idMedicamento"),
                            rs.getString("nombre"),
                            rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamento;
    }

    public Medicamento buscarPorNombreOId(String criterio) {
        Medicamento medicamento = null;
        String sql = "SELECT * FROM medicamento WHERE nombre = ? OR idMedicamento = ? LIMIT 1";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, criterio);
            pstmt.setString(2, criterio);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    medicamento = new Medicamento(
                            rs.getString("idMedicamento"),
                            rs.getString("nombre"),
                            rs.getString("descripcion")
                            // Si tu constructor tiene más campos, agrégalos aquí
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamento;
    }


}
