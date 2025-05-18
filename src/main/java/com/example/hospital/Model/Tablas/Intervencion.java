package com.example.hospital.Model.Tablas;

public class Intervencion {
    private String idIntervencion;
    private String nombre;
    private String descripcion;
    private String idDepartamento; // Parte de la clave compuesta de Sala
    private String numero;         // Parte de la clave compuesta de Sala

    public Intervencion(String idIntervencion, String nombre, String descripcion, String idDepartamento, String numero) {
        this.idIntervencion = idIntervencion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idDepartamento = idDepartamento;
        this.numero = numero;
    }

    public String getIdIntervencion() {
        return idIntervencion;
    }

    public void setIdIntervencion(String idIntervencion) {
        this.idIntervencion = idIntervencion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Intervencion{" +
                "idIntervencion='" + idIntervencion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idDepartamento='" + idDepartamento + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}

