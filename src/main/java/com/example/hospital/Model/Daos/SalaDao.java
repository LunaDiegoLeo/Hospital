package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.TipoSala;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.hospital.Model.Tablas.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaDao extends Dao<Sala> {
    public SalaDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Sala sala) {
        String sql = "INSERT INTO Sala (IDDepartamento, Numero, TipoSala) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sala.getIdDepartamento());
            stmt.setInt(2, sala.getNumero());
            TipoSala tipoSala= consultarPorNombre(sala.getTipoSala());
            stmt.setInt(3, tipoSala.getIdTipoSala());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String[] partes = id.split("-");
        if (partes.length != 2) return false; // validación
        String idDepartamento = partes[0];
        int numero = Integer.parseInt(partes[1]);

        String sql = "DELETE FROM Sala WHERE IDDepartamento = ? AND Numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idDepartamento);
            stmt.setInt(2, numero);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Sala consultar(String id) {
        String[] partes = id.split("-");
        if (partes.length != 2) return null;
        String idDepartamento = partes[0];
        int numero = Integer.parseInt(partes[1]);

        String sql = "SELECT s.IDDepartamento, s.Numero, ts.TipoSala, ts.Descripcion " +
                "FROM Sala s JOIN TipoSala ts ON s.TipoSala = ts.TipoSala " +
                "WHERE s.IDDepartamento = ? AND s.Numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idDepartamento);
            stmt.setInt(2, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TipoSala tipoSala = new TipoSala(rs.getInt("TipoSala"), rs.getString("Descripcion"));
                return new Sala(rs.getString("IDDepartamento"), rs.getInt("Numero"), tipoSala.getDescripcion());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean modificar(Sala sala) {
        String sql = "UPDATE Sala SET TipoSala = ? WHERE IDDepartamento = ? AND Numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            TipoSala tipoSala= consultarPorNombre(sala.getTipoSala());
            stmt.setInt(1, tipoSala.getIdTipoSala());
            stmt.setString(2, sala.getIdDepartamento());
            stmt.setInt(3, sala.getNumero());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TipoSala consultarPorNombre(String descripcion) {
        String sql = "SELECT TipoSala, Descripcion FROM TipoSala WHERE Descripcion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, descripcion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TipoSala(rs.getInt("TipoSala"), rs.getString("Descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ObservableList<Sala> listar() {
        ObservableList<Sala> salas = FXCollections.observableArrayList();
        String sql = "SELECT s.IDDepartamento, s.Numero, ts.TipoSala, ts.Descripcion " +
                "FROM Sala s JOIN TipoSala ts ON s.TipoSala = ts.TipoSala ORDER BY s.IDDepartamento,s.Numero";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TipoSala tipoSala = new TipoSala(rs.getInt("TipoSala"), rs.getString("Descripcion"));
                Sala sala = new Sala(rs.getString("IDDepartamento"), rs.getInt("Numero"), tipoSala.getDescripcion());
                salas.add(sala);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salas;
    }
}
