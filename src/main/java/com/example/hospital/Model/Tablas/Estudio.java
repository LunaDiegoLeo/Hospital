package com.example.hospital.Model.Tablas;

public class Estudio {
    // Atributos privados
    private int idEstudio;
    private String descripcion;
    private double costo;
    private String idLaboratorio; // Relación directa con Laboratorio


    // Constructor con parámetros
    public Estudio(int idEstudio, String descripcion, double costo, String idLaboratorio) {
        this.idEstudio = idEstudio;
        this.descripcion = descripcion;
        this.costo = costo;
        this.idLaboratorio = idLaboratorio;
    }

    // Getters y Setters

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(String idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }
}
