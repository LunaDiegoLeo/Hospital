package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Doctor;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.util.ArrayList;

public class DoctorDao extends Dao<Doctor> {
    public DoctorDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Doctor doctor) {
        String sql = "INSERT INTO Doctor (idDoctor, nombre, telefono, curp, cedula, rfc, añosExperiencia, sueldoPorDia, idEspecialidad, idDepartamento, idTurno) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, doctor.getIdDoctor());
            preparedStatement.setString(2, doctor.getNombre());
            preparedStatement.setString(3, doctor.getTelefono());
            preparedStatement.setString(4, doctor.getCurp());
            preparedStatement.setString(5, doctor.getCedula());
            preparedStatement.setString(6, doctor.getRfc());
            preparedStatement.setInt(7, doctor.getAniosExperiencia());
            preparedStatement.setDouble(8, doctor.getSueldoPorDia());
            preparedStatement.setInt(9, doctor.getIdEspecialidad());
            preparedStatement.setString(10, doctor.getIdDepartamento());
            preparedStatement.setInt(11, doctor.getIdTurno());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Doctor WHERE idDoctor = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modificar(Doctor doctor) {
        String sql = "UPDATE Doctor SET nombre = ?, telefono = ?, curp = ?, cedula = ?, rfc = ?, añosExperiencia = ?, sueldoPorDia = ?, idEspecialidad = ?, idDepartamento = ?, idTurno = ? WHERE idDoctor = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, doctor.getNombre());
            preparedStatement.setString(2, doctor.getTelefono());
            preparedStatement.setString(3, doctor.getCurp());
            preparedStatement.setString(4, doctor.getCedula());
            preparedStatement.setString(5, doctor.getRfc());
            preparedStatement.setInt(6, doctor.getAniosExperiencia());
            preparedStatement.setDouble(7, doctor.getSueldoPorDia());
            preparedStatement.setInt(8, doctor.getIdEspecialidad());
            preparedStatement.setString(9, doctor.getIdDepartamento());
            preparedStatement.setInt(10, doctor.getIdTurno());
            preparedStatement.setString(11, doctor.getIdDoctor());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Doctor consultar(String id) {
        String sql = "SELECT * FROM Doctor WHERE idDoctor = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Doctor(
                            resultSet.getString("idDoctor"),
                            resultSet.getString("nombre"),
                            resultSet.getString("telefono"),
                            resultSet.getString("curp"),
                            resultSet.getString("cedula"),
                            resultSet.getString("rfc"),
                            resultSet.getInt("añosExperiencia"),
                            resultSet.getDouble("sueldoPorDia"),
                            resultSet.getInt("idEspecialidad"),
                            resultSet.getString("idDepartamento"),
                            resultSet.getInt("idTurno")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Doctor> listar() {
        String sql = "SELECT * FROM Doctor";
        try (var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {

            var doctors = new ArrayList<Doctor>();
            while (resultSet.next()) {
                doctors.add(new Doctor(
                        resultSet.getString("idDoctor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("curp"),
                        resultSet.getString("cedula"),
                        resultSet.getString("rfc"),
                        resultSet.getInt("añosExperiencia"),
                        resultSet.getDouble("sueldoPorDia"),
                        resultSet.getInt("idEspecialidad"),
                        resultSet.getString("idDepartamento"),
                        resultSet.getInt("idTurno")
                ));
            }
            return javafx.collections.FXCollections.observableArrayList(doctors);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return javafx.collections.FXCollections.observableArrayList();
    }
}
