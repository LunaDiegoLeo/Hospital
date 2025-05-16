package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Doctor;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class DoctorDao extends Dao<Doctor> {
    public DoctorDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Doctor doctor) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(Doctor doctor) {
        return false;
    }

    @Override
    public Doctor consultar(String id) {
        return null;
    }

    @Override
    public ObservableList<Doctor> listar() {
        return null;
    }
}
