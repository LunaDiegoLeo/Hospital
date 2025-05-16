package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Adminis;
import com.example.hospital.Model.Tablas.Seguro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeguroDao extends Dao<Seguro> {
    public SeguroDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Seguro seguro) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(Seguro seguro) {
        return false;
    }

    @Override
    public Seguro consultar(String id) {
        return null;
    }

    @Override
    public ObservableList<Seguro> listar() {
        String sql = "SELECT * FROM seguro";
        ObservableList<Seguro> add= FXCollections.observableArrayList();
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Seguro seguro= new Seguro(rs.getString("IDseguro"),rs.getString("Telefono"),rs.getDouble("costo"),rs.getInt("IDTipoCob"),rs.getString("IDCompañia"),rs.getDouble("CostoCobertura") );
                add.add(seguro);
            }
            if(add.isEmpty()){
                return null;
            } else{
                return add;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
