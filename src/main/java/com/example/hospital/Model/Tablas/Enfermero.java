package com.example.hospital.Model.Tablas;

public class Enfermero {
    // Atributos privados
    private String idEnfermero;
    private String nombre;
    private String curp;
    private String cedula;
    private String rfc;
    private double sueldoPorDia;
    private int idTurno;
    private String idDepartamento;

    // Constructor vacío
    public Enfermero() {
    }

    // Constructor con parámetros
    public Enfermero(String idEnfermero, String nombre, String curp, String cedula, String rfc,
                     double sueldoPorDia, int idTurno, String idDepartamento) {
        this.idEnfermero = idEnfermero;
        this.nombre = nombre;
        this.curp = curp;
        this.cedula = cedula;
        this.rfc = rfc;
        this.sueldoPorDia = sueldoPorDia;
        this.idTurno = idTurno;
        this.idDepartamento = idDepartamento;
    }

    // Getters y Setters

    public String getIdEnfermero() {
        return idEnfermero;
    }

    public void setIdEnfermero(String idEnfermero) {
        this.idEnfermero = idEnfermero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public double getSueldoPorDia() {
        return sueldoPorDia;
    }

    public void setSueldoPorDia(double sueldoPorDia) {
        this.sueldoPorDia = sueldoPorDia;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
