package com.example.hospital.Model.Admin;

import com.example.hospital.Model.Adminis;
import com.example.hospital.Model.Dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminDao extends Dao<Adminis>{
    public AdminDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Adminis adminis) {
        String sql="INSERT INTO admin VALUES (?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, adminis.getId());
            stmt.setString(2,adminis.getNombre());

            stmt.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(String id) {
        return false;
    }

    @Override
    public Adminis consultar(String id) {
        return null;
    }

    @Override
    public ArrayList<Adminis> listar() {
        String sql = "SELECT * FROM admin";
        ArrayList<Adminis> add= new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Adminis adminis = new Adminis(rs.getString("IdAdmin"), rs.getString("Nombre"));
                add.add(adminis);

            }
            return add;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
