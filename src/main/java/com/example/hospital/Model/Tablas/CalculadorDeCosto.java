package com.example.hospital.Model.Tablas;

import com.example.hospital.Model.CostoStrategy;

public class CalculadorDeCosto {
    private CostoStrategy estrategia;

    public CalculadorDeCosto(CostoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(CostoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(CitaMedica cita) {
        return estrategia.calcularCosto(cita);
    }
}

