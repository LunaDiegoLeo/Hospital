package com.example.hospital.Model.Tablas;

public class Presentacion {
    private int idPresentacion;
    private int cantidadUnidad;
    private String unidad;
    private String descripcion;

    public Presentacion(int idPresentacion, int cantidadUnidad, String unidad, String descripcion) {
        this.idPresentacion = idPresentacion;
        this.cantidadUnidad = cantidadUnidad;
        this.unidad = unidad;
        this.descripcion = descripcion;
    }

    public int getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(int idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public int getCantidadUnidad() {
        return cantidadUnidad;
    }

    public void setCantidadUnidad(int cantidadUnidad) {
        this.cantidadUnidad = cantidadUnidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion; // útil para ComboBox u otros controles
    }
}

