package com.example.hospital.Model.Tablas;

public class Sala {
    private String idDepartamento;
    private int numero;
    private String tipoSala;

    // Constructor
    public Sala(String idDepartamento, int numero, String tipoSala) {
        this.idDepartamento = idDepartamento;
        this.numero = numero;
        this.tipoSala = tipoSala;
    }

    // Getters y Setters
    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }


}

