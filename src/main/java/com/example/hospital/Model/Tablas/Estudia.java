package com.example.hospital.Model.Tablas;

public class Estudia {
    private String idDoctor;
    private String idPaciente;
    private String idEstudio;

    public Estudia(String idDoctor, String idPaciente, String idEstudio) {
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.idEstudio = idEstudio;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(String idEstudio) {
        this.idEstudio = idEstudio;
    }

    @Override
    public String toString() {
        return "Estudia{" +
                "idDoctor='" + idDoctor + '\'' +
                ", idPaciente='" + idPaciente + '\'' +
                ", idEstudio='" + idEstudio + '\'' +
                '}';
    }
}
