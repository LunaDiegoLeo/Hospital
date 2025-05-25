package com.example.hospital.Model;

import com.example.hospital.Model.Tablas.CitaMedica;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public interface CostoStrategy {
    double calcularCosto(CitaMedica cita);

    public class CostoEstandarStrategy implements CostoStrategy {
        @Override
        public double calcularCosto(CitaMedica cita) {
            return 350.0; // Costo fijo base
        }
    }

    public class CostoFlexibleStrategy implements CostoStrategy {

        @Override
        public double calcularCosto(CitaMedica cita) {
            double base = obtenerCostoBase();
            base += calcularRecargoDepartamento(cita.getIdDepartamento());
            base += calcularRecargoHora(cita.getHora());
            base = aplicarRecargoFinDeSemana(base, cita.getFecha());
            return base;
        }

        private double obtenerCostoBase() {
            return 300.0;
        }

        private double calcularRecargoDepartamento(String idDepartamento) {
            return switch (idDepartamento) {
                case "DEP01", "DEP03" -> 50.0;
                case "DEP08" -> 75.0;
                default -> 0.0;
            };
        }

        private double calcularRecargoHora(LocalTime hora) {
            if (hora != null && hora.isAfter(LocalTime.of(18, 0))) {
                return 30.0;
            }
            return 0.0;
        }

        private double aplicarRecargoFinDeSemana(double base, LocalDate fecha) {
            if (fecha != null) {
                DayOfWeek dia = fecha.getDayOfWeek();
                if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {
                    return base * 1.15;
                }
            }
            return base;
        }


    }
}



