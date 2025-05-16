package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Turno;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class TurnoDao extends Dao<Turno> {
    public TurnoDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Turno turno) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(Turno turno) {
        return false;
    }

    @Override
    public Turno consultar(String id) {
        return null;
    }

    @Override
    public ObservableList<Turno> listar() {
        return null;
    }
}
