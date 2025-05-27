package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.Tablas.SeInterviene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static com.example.hospital.Model.ConexioBD.connection;

public class SeIntervieneDao extends Dao<SeInterviene> {

    public SeIntervieneDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(SeInterviene seInterviene) {
        String sql = "INSERT INTO seinterviene (IDDoctor, IDPaciente, IDIntervencion, Costo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, seInterviene.getIdDoctor());
            stmt.setString(2, seInterviene.getIdPaciente());
            stmt.setString(3, seInterviene.getIdIntervencion());
            stmt.setDouble(4, seInterviene.getCosto());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        // Aquí asumimos que 'id' es el idIntervencion para simplificar. Podrías cambiarlo si quieres que sea por clave compuesta.
        String sql = "DELETE FROM seinterviene WHERE IDIntervencion=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(SeInterviene seInterviene) {
        String sql = "UPDATE seinterviene SET IDDoctor=?, IDPaciente=?, Costo=? WHERE IDIntervencion=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, seInterviene.getIdDoctor());
            stmt.setString(2, seInterviene.getIdPaciente());
            stmt.setDouble(3, seInterviene.getCosto());
            stmt.setString(4, seInterviene.getIdIntervencion());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SeInterviene consultar(String idIntervencion) {
        String sql = "SELECT * FROM seinterviene WHERE IDIntervencion=?";
        SeInterviene seInterviene = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idIntervencion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                seInterviene = new SeInterviene(
                        rs.getString("IDDoctor"),
                        rs.getString("IDPaciente"),
                        rs.getString("IDIntervencion"),
                        rs.getDouble("Costo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seInterviene;
    }

    @Override
    public ObservableList<SeInterviene> listar() {
        ObservableList<SeInterviene> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM seinterviene";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                SeInterviene seInterviene = new SeInterviene(
                        rs.getString("IDDoctor"),
                        rs.getString("IDPaciente"),
                        rs.getString("IDIntervencion"),
                        rs.getDouble("Costo")
                );
                lista.add(seInterviene);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
