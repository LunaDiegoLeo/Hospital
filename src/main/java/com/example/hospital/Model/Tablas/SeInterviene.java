package com.example.hospital.Model.Tablas;


public class SeInterviene {

    private String idDoctor;
    private String idPaciente;
    private String idIntervencion;
    private double costo;

    public SeInterviene() {
    }

    public SeInterviene(String idDoctor, String idPaciente, String idIntervencion, double costo) {
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.idIntervencion = idIntervencion;
        this.costo = costo;
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

    public String getIdIntervencion() {
        return idIntervencion;
    }

    public void setIdIntervencion(String idIntervencion) {
        this.idIntervencion = idIntervencion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }


}

