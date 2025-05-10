package com.example.hospital.Model;

import com.example.hospital.Model.Admin.PacienteDao;

import java.sql.Connection;

public enum DaoFactory {
    PACIENTE {
        public Dao<?> crear(Connection connection) {
            return new PacienteDao(connection);
        }
    };

    public abstract Dao<?> crear(Connection connection);
}
