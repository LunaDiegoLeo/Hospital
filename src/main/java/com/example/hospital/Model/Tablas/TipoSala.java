package com.example.hospital.Model.Tablas;

public class TipoSala {
    private int idTipoSala;
    private String descripcion;

    public TipoSala(int idTipoSala, String descripcion) {
        this.idTipoSala = idTipoSala;
        this.descripcion = descripcion;
    }

    public int getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(int idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoSala{" +
                "ID=" + idTipoSala +
                ", Descripcion='" + descripcion + '\'' +
                '}';
    }
}
