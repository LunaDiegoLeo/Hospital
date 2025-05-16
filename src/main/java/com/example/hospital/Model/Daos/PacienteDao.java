package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Paciente;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PacienteDao extends Dao<Paciente> {
    public PacienteDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Paciente paciente) {
        String sql = "INSERT INTO paciente (Idpaciente, nombre, sexo, telefono, edad, altura, peso, FechaNacimiento, curp, IDseguro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, paciente.getIdPaciente());
            stmt.setString(2, paciente.getNombre());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, paciente.getTelefono());
            stmt.setInt(5, paciente.getEdad());
            stmt.setDouble(6, paciente.getAltura());
            stmt.setDouble(7, paciente.getPeso());
            stmt.setString(8, paciente.getFechaNacimiento());
            stmt.setString(9, paciente.getCurp());
            stmt.setString(10, paciente.getIdSeguro());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM paciente WHERE Idpaciente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modificar(Paciente paciente) {
        String sql = "UPDATE paciente SET nombre = ?, sexo = ?, telefono = ?, edad = ?, altura = ?, peso = ?, FechaNacimiento = ?, curp = ?, IDseguro = ? WHERE Idpaciente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getSexo());
            stmt.setString(3, paciente.getTelefono());
            stmt.setInt(4, paciente.getEdad());
            stmt.setDouble(5, paciente.getAltura());
            stmt.setDouble(6, paciente.getPeso());
            stmt.setString(7, paciente.getFechaNacimiento());
            stmt.setString(8, paciente.getCurp());
            stmt.setString(9, paciente.getIdSeguro());
            stmt.setString(10, paciente.getIdPaciente());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Paciente consultar(String id) {
        String sql = "SELECT * FROM paciente WHERE Idpaciente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Paciente(
                            rs.getString("Idpaciente"),
                            rs.getString("nombre"),
                            rs.getString("sexo"),
                            rs.getString("telefono"),
                            rs.getInt("edad"),
                            rs.getDouble("altura"),
                            rs.getDouble("peso"),
                            rs.getString("FechaNacimiento"),
                            rs.getString("curp"),
                            rs.getString("IDseguro")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Paciente> listar() {
        ObservableList<Paciente> pacientes = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT * FROM paciente";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getString("Idpaciente"),
                        rs.getString("nombre"),
                        rs.getString("sexo"),
                        rs.getString("telefono"),
                        rs.getInt("edad"),
                        rs.getDouble("altura"),
                        rs.getDouble("peso"),
                        rs.getString("FechaNacimiento"),
                        rs.getString("curp"),
                        rs.getString("IDseguro")
                );
                pacientes.add(paciente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pacientes;
    }


}
