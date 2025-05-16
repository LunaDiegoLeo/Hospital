package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Especialidad;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class EspecialidadDao extends Dao<Especialidad> {
    public EspecialidadDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Especialidad especialidad) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(Especialidad especialidad) {
        return false;
    }

    @Override
    public Especialidad consultar(String id) {
        return null;
    }

    @Override
    public ObservableList<Especialidad> listar() {
        return null;
    }
}
