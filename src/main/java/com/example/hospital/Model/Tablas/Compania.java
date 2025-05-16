package com.example.hospital.Model.Tablas;

public class Compania {
    // Atributos privados
    private String idCompania;
    private String nombre;

    // Constructor vacío
    public Compania() {}

    // Constructor con parámetros
    public Compania(String idCompania, String nombre) {
        this.idCompania = idCompania;
        this.nombre = nombre;
    }

    // Getters y Setters

    public String getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(String idCompania) {
        this.idCompania = idCompania;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

