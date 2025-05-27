package com.example.hospital.Model;


import com.example.hospital.Model.Tablas.Paciente;

import java.util.List;

public class CalculadorCosto {
    private List<CostoStrategy2> estrategias;
    private double costoBase;

    public CalculadorCosto(double costoBase, List<CostoStrategy2> estrategias) {
        this.costoBase = costoBase;
        this.estrategias = estrategias;
    }

    public double calcularCostoFinal(Paciente paciente) {
        double costo = costoBase;
        for (CostoStrategy2 estrategia : estrategias) {
            costo = estrategia.calcularCosto(paciente, costo);
        }
        return costo;
    }
}

