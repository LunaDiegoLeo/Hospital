package com.example.hospital.Model.Tablas;

public class SePresenta {
    private int idPresentacion;
    private int idTipoM;
    private String idMedicamento;
    private int cantidad;
    private double costo;

    public SePresenta() {
    }

    public SePresenta(int idPresentacion, int idTipoM, String idMedicamento, int cantidad, double costo) {
        this.idPresentacion = idPresentacion;
        this.idTipoM = idTipoM;
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public int getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(int idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public int getIdTipoM() {
        return idTipoM;
    }

    public void setIdTipoM(int idTipoM) {
        this.idTipoM = idTipoM;
    }

    public String getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(String idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "SePresenta{" +
                "idPresentacion=" + idPresentacion +
                ", idTipoM=" + idTipoM +
                ", idMedicamento='" + idMedicamento + '\'' +
                ", cantidad=" + cantidad +
                ", costo=" + costo +
                '}';
    }
}

