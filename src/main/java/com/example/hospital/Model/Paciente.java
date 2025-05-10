package com.example.hospital.Model;

public class Paciente {
    private String idPaciente;
    private String nombre;
    private String curp;
    private String sexo;
    private String fechaNacimiento;
    private int edad;
    private double peso;
    private double altura;
    private String telefono;
    private String idSeguro;

    public Paciente(String idPaciente, String nombre, String sexo, String telefono, int edad, double altura, double peso, String fechaNacimiento, String curp, String idSeguro){
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.curp = curp;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.telefono = telefono;
        this.idSeguro = idSeguro;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(String idSeguro) {
        this.idSeguro = idSeguro;
    }
}
