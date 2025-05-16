package com.example.hospital.Model.Daos;

import java.sql.Connection;

public enum DaoFactory {
    PACIENTE {
        public Dao<?> crear(Connection connection) {
            return new PacienteDao(connection);
        }
    },
    ADMIN {
        public Dao<?> crear(Connection connection) {
            return new AdminDao(connection);
        }
    },
    USUARIO{
        public Dao<?> crear(Connection connection) {
            return new UsuarioDao(connection);
        }
    },
    SEGURO{
        public Dao<?> crear(Connection connection) {
            return new SeguroDao(connection);
        }
    };

    public abstract Dao<?> crear(Connection connection);
}
