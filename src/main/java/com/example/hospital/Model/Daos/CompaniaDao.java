package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Compania;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CompaniaDao extends Dao<Compania> {
    public CompaniaDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Compania compania) {
        String sql = "INSERT INTO compañia (idCompañia, nombre) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, compania.getIdCompania());
            stmt.setString(2, compania.getNombre());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM compañia WHERE idCompañia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Compania compania) {
        String sql = "UPDATE compañia SET nombre = ? WHERE idCompañia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, compania.getNombre());
            stmt.setString(2, compania.getIdCompania());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Compania consultar(String id) {
        String sql = "SELECT * FROM compañia WHERE idCompañia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Compania(
                        resultSet.getString("idCompañia"),
                        resultSet.getString("nombre")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Compania> listar() {
        String sql = "SELECT * FROM compañia";
        ObservableList<Compania> companias = FXCollections.observableArrayList();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                companias.add(new Compania(
                        resultSet.getString("idCompañia"),
                        resultSet.getString("nombre")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companias;
    }
}
