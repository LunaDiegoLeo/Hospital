package com.example.hospital.Model.Tablas;

public class Seguro {
    private String id;
    private String telefono;
    private double precio;
    private int idTipoCobertura;
    private String IDCompania;
    private double costocobertura;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdTipoCobertura() {
        return idTipoCobertura;
    }

    public void setIdTipoCobertura(int idTipoCobertura) {
        this.idTipoCobertura = idTipoCobertura;
    }

    public String getIDCompania() {
        return IDCompania;
    }

    public void setIDCompania(String IDCompania) {
        this.IDCompania = IDCompania;
    }

    public double getCostocobertura() {
        return costocobertura;
    }

    public void setCostocobertura(double costocobertura) {
        this.costocobertura = costocobertura;
    }
    public Seguro(String id, String telefono, double precio, int idTipoCobertura, String IDCompania, double costocobertura) {
        this.id = id;
        this.telefono = telefono;
        this.precio = precio;
        this.idTipoCobertura = idTipoCobertura;
        this.IDCompania = IDCompania;
        this.costocobertura = costocobertura;
    }
}
