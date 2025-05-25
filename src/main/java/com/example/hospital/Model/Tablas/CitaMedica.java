package com.example.hospital.Model.Tablas;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaMedica {
    private String idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String idPaciente;
    private String idDepartamento;
    private double costo;
    private String idDoctor;

    // Constructor
    public CitaMedica(String idCita, String hora, String fecha, String idPaciente, String idDepartamento, double costo, String idDoctor) {
        this.idCita = idCita;
        this.hora = LocalTime.parse(hora);
        this.fecha = LocalDate.parse(fecha);
        this.idPaciente = idPaciente;
        this.idDepartamento = idDepartamento;
        this.costo = costo;
        this.idDoctor = idDoctor;
    }

    public CitaMedica(){

    }

    // Getters y Setters
    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = LocalTime.parse(hora);
    }


    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha);
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    // Método toString para impresión
    @Override
    public String toString() {
        return "CitaMedica{" +
                "idCita='" + idCita + '\'' +
                ", hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                ", idPaciente='" + idPaciente + '\'' +
                ", idDepartamento='" + idDepartamento + '\'' +
                ", costo=" + costo +
                ", idDoctor='" + idDoctor + '\'' +
                '}';
    }
}

