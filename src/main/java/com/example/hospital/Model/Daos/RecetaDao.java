package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Receta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RecetaDao extends Dao<Receta> {
    public RecetaDao(Connection con) {
        super(con);
    }

    public boolean insertar(Receta receta) {
        String sql = "INSERT INTO receta (IDDoctor, IDPaciente, IDMedicamento, Dosis, Fecha, Hora) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, receta.getIdDoctor());
            stmt.setString(2, receta.getIdPaciente());
            stmt.setString(3, receta.getIdMedicamento());
            stmt.setString(4, receta.getDosis());

            // Obtener fecha y hora actuales
            LocalDate fechaActual = LocalDate.now();
            LocalTime horaActual = LocalTime.now();

            stmt.setString(5, fechaActual.toString()); // yyyy-MM-dd
            stmt.setString(6, horaActual.withNano(0).toString()); // HH:mm:ss

            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<Receta> listar() {
        ObservableList<Receta> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receta";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Receta receta = new Receta(
                        rs.getString("IDDoctor"),
                        rs.getString("IDPaciente"),
                        rs.getString("IDMedicamento"),
                        rs.getString("Dosis"),
                        rs.getString("Fecha"),
                        rs.getString("Hora")
                );
                lista.add(receta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Receta consultar(String idDoctor) {
        String sql = "SELECT * FROM receta WHERE IDDoctor = ? AND IDPaciente = ? AND IDMedicamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idDoctor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Receta(
                            rs.getString("IDDoctor"),
                            rs.getString("IDPaciente"),
                            rs.getString("IDMedicamento"),
                            rs.getString("Dosis"),
                            rs.getString("Fecha"),
                            rs.getString("Hora")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminar(String idDoctor) {
        String sql = "DELETE FROM receta WHERE IDDoctor = ? AND IDPaciente = ? AND IDMedicamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idDoctor);

            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modificar(Receta receta) {
        String sql = "UPDATE receta SET Dosis = ? WHERE IDDoctor = ? AND IDPaciente = ? AND IDMedicamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, receta.getDosis());
            stmt.setString(2, receta.getIdDoctor());
            stmt.setString(3, receta.getIdPaciente());
            stmt.setString(4, receta.getIdMedicamento());
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
