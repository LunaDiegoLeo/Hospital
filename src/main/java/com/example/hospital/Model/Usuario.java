package com.example.hospital.Model;

public class Usuario {
    private String usuario;
    private String password;
    private String idEx;
    private int tipo;

    public Usuario(String usuario, String password, String idEx, int tipo) {
        this.usuario = usuario;
        this.password = password;
        this.idEx = idEx;
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdEx() {
        return idEx;
    }

    public void setIdEx(String idEx) {
        this.idEx = idEx;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
