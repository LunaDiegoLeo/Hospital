package com.example.hospital.Model.Tablas;

public class Hace {
    // Atributos privados
    private String idLaboratorio;
    private int idEstudio;

    // Constructor vacío
    public Hace() {}

    // Constructor con parámetros
    public Hace(String idLaboratorio, int idEstudio) {
        this.idLaboratorio = idLaboratorio;
        this.idEstudio = idEstudio;
    }

    // Getters y Setters

    public String getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(String idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }
}
