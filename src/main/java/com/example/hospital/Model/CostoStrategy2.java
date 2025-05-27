package com.example.hospital.Model;

import com.example.hospital.Model.Tablas.Paciente;

public interface CostoStrategy2 {
    double calcularCosto(Paciente paciente, double costoBase);

    public class CostoPorEdadStrategy implements CostoStrategy2 {
        @Override
        public double calcularCosto(Paciente paciente, double costoBase) {
            int edad = paciente.getEdad();
            if (edad > 60) {
                return costoBase * 1.2; // +20% si es adulto mayor
            } else if (edad < 18) {
                return costoBase * 0.85; // -15% si es menor de edad
            }
            return costoBase;
        }
    }



    public class CostoPorPesoStrategy implements CostoStrategy2 {
        @Override
        public double calcularCosto(Paciente paciente, double costoBase) {
            if (paciente.getPeso() > 100) {
                return costoBase * 1.1; // +10% si pesa más de 100 kg
            }
            return costoBase;
        }
    }


    public class CostoPorSeguroStrategy implements CostoStrategy2 {
        @Override
        public double calcularCosto(Paciente paciente, double costoBase) {
            if (paciente.getIdSeguro() != null && !paciente.getIdSeguro().isEmpty()) {
                return costoBase * 0.9; // -10% si tiene seguro
            }
            return costoBase;
        }
    }


}



