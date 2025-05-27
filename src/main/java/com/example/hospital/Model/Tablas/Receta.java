package com.example.hospital.Model.Tablas;

public class Receta {
    private String idDoctor;
    private String idPaciente;
    private String idMedicamento;
    private String dosis;
    private String fecha; // yyyy-MM-dd
    private String hora;  // HH:mm:ss

    public Receta(String idDoctor, String idPaciente, String idMedicamento, String dosis, String fecha, String hora) {
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.idMedicamento = idMedicamento;
        this.dosis = dosis;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y setters

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

    public String getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(String idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
