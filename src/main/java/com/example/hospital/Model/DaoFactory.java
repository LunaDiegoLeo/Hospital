package com.example.hospital.Model;

import com.example.hospital.Model.Admin.AdminDao;
import com.example.hospital.Model.Admin.PacienteDao;
import com.example.hospital.Model.Admin.UsuarioDao;

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
    };

    public abstract Dao<?> crear(Connection connection);
}
