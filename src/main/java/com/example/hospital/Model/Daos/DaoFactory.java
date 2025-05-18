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
    },
    TURNO{
        public Dao<?> crear(Connection connection) {
            return new TurnoDao(connection);
        }
    },
    DEPARTAMENTO{
        public Dao<?> crear(Connection connection) {
            return new DepartamentoDao(connection);
        }
    },
    ESPECIALIDAD{
        public Dao<?> crear(Connection connection) {
            return new EspecialidadDao(connection);
        }
    },
    DOCTOR{
        public Dao<?> crear(Connection connection) {
            return new DoctorDao(connection);
        }
    },
    ENFERMERO{
        public Dao<?> crear(Connection connection) {
            return new EnfermeroDao(connection);
        }
    },
    COMPANIA{
        public Dao<?> crear(Connection connection) {
            return new CompaniaDao(connection);
        }
    },
    LABORATORIO{
        public Dao<?> crear(Connection connection) {
            return new LaboratorioDao(connection);
        }
    },
    ESTUDIO{
        public Dao<?> crear(Connection connection) {
            return new EstudioDao(connection);
        }
    },
    HACE{
        public Dao<?> crear(Connection connection) {
            return new HaceDao(connection);
        }
    },
    SALA{
        public Dao<?> crear(Connection connection) {
            return new SalaDao(connection);
        }
    },
    TIPOSALA{
        public Dao<?> crear(Connection connection) {
            return new TipoSalaDao(connection);
        }
    },
    INTERVENCION{
        public Dao<?> crear(Connection connection) {
            return new IntervencionDao(connection);
        }
    },
    PRESENTACION {
        public Dao<?> crear(Connection connection) {
            return new PresentacionDao(connection);
        }
    },
    MEDICAMENTO {
        public Dao<?> crear(Connection connection) {
            return new MedicamentoDao(connection);
        }
    },
    SEPRESENTA {
        public Dao<?> crear(Connection connection) {
            return new SePresentaDao(connection);
        }
    },
    TIPOMEDICAMENTO {
        public Dao<?> crear(Connection connection) {
            return new TipoMedicamentoDao(connection);
        }
    };

    public abstract Dao<?> crear(Connection connection);
}
