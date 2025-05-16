package com.example.hospital.Model.Tablas;

public class Doctor {
    private String idDoctor;
    private String nombre;
    private String telefono;
    private String curp;
    private String cedula;
    private String rfc;
    private int aniosExperiencia;
    private double sueldoPorDia;
    private int idEspecialidad;
    private String idDepartamento;
    private int idTurno;

    // Constructor completo
    public Doctor(String idDoctor, String nombre, String telefono, String curp, String cedula, String rfc,
                  int aniosExperiencia, double sueldoPorDia, int idEspecialidad, String idDepartamento, int idTurno) {
        this.idDoctor = idDoctor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.curp = curp;
        this.cedula = cedula;
        this.rfc = rfc;
        this.aniosExperiencia = aniosExperiencia;
        this.sueldoPorDia = sueldoPorDia;
        this.idEspecialidad = idEspecialidad;
        this.idDepartamento = idDepartamento;
        this.idTurno = idTurno;
    }

    // Getters y Setters
    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public double getSueldoPorDia() {
        return sueldoPorDia;
    }

    public void setSueldoPorDia(double sueldoPorDia) {
        this.sueldoPorDia = sueldoPorDia;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }
}

