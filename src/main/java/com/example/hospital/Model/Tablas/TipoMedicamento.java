package com.example.hospital.Model.Tablas;

public class TipoMedicamento {
    private int idTipoM;
    private String descripcion;

    public TipoMedicamento(int idTipoM, String descripcion) {
        this.idTipoM = idTipoM;
        this.descripcion = descripcion;
    }

    public int getIdTipoM() {
        return idTipoM;
    }

    public void setIdTipoM(int idTipoM) {
        this.idTipoM = idTipoM;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion; // útil para mostrar en ComboBox o SplitMenuButton
    }
}

