package com.example.hospital.Model;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class Dao <T>{
    protected Connection connection;

    public Dao(Connection connection){
        this.connection = connection;
    }

    public abstract boolean insertar(T t);
    public abstract boolean eliminar(String id);
    public abstract boolean modificar(String id);
    public abstract T consultar(String id);
    public abstract ArrayList<T> listar();


}
