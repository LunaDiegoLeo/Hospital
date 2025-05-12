package com.example.hospital.Model.Admin;

import com.example.hospital.Model.Dao;
import com.example.hospital.Model.Paciente;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.util.ArrayList;

public class PacienteDao extends Dao<Paciente> {
    public PacienteDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Paciente paciente) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(Paciente paciente) {
        return false;
    }

    @Override
    public Paciente consultar(String id) {
        return null;
    }

    @Override
    public ObservableList<Paciente> listar() {
        return null;
    }


}
