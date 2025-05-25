package com.example.hospital.Model.Daos;


import javafx.collections.ObservableList;

import java.sql.Connection;

public abstract class Dao <T>{
    protected Connection connection;

    public Dao(Connection connection){
        this.connection = connection;
    }

    public abstract boolean insertar(T t);
    public abstract boolean eliminar(String id);
    public abstract boolean modificar(T t);
    public abstract T consultar(String id);
    public abstract ObservableList<T> listar();


}