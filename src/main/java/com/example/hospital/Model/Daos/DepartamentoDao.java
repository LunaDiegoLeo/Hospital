package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Departamento;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class DepartamentoDao extends Dao<Departamento> {
    public DepartamentoDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Departamento departamento) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(Departamento departamento) {
        return false;
    }

    @Override
    public Departamento consultar(String id) {
        return null;
    }

    @Override
    public ObservableList<Departamento> listar() {
        return null;
    }
}
