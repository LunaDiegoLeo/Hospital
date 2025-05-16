package com.example.hospital.Model.Tablas;

public class Laboratorio {
    // Atributos privados
    private String idLaboratorio;
    private String descripcion;
    private int piso;

    // Constructor vacío
    public Laboratorio() {}

    // Constructor con parámetros
    public Laboratorio(String idLaboratorio, String descripcion, int piso) {
        this.idLaboratorio = idLaboratorio;
        this.descripcion = descripcion;
        this.piso = piso;
    }

    // Getters y Setters

    public String getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(String idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
