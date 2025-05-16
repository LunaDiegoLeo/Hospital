package com.example.hospital.Model.Tablas;

public class Departamento {
    private String idDepartamento;
    private String descripcion;
    private int piso;

    // Constructor
    public Departamento(String idDepartamento, String descripcion, int piso) {
        this.idDepartamento = idDepartamento;
        this.descripcion = descripcion;
        this.piso = piso;
    }

    // Getters y Setters
    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
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
