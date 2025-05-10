package com.example.hospital.Model;

import java.sql.Connection;

public abstract class Dao <T>{
    protected Connection connection;

    public Dao(Connection connection){
        this.connection = connection;
    }

    public abstract void insertar(T t);
    public abstract void eliminar(T t);
    public abstract void modificar(T t);
    public abstract T consultar(T t);
    public abstract void listar();

}
