package com.example.hospital.Model.Admin;

import com.example.hospital.Model.Dao;
import com.example.hospital.Model.Paciente;

import java.sql.Connection;

public class PacienteDao extends Dao<Paciente> {
    public PacienteDao(Connection connection) {
        super(connection);
    }

    @Override
    public void insertar(Paciente paciente) {

    }

    @Override
    public void eliminar(Paciente paciente) {

    }

    @Override
    public void modificar(Paciente paciente) {

    }

    @Override
    public Paciente consultar(Paciente paciente) {
        return null;
    }

    @Override
    public void listar() {

    }

    @Override
    public void exitir() {

    }
}
