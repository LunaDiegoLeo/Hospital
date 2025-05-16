package com.example.hospital.Model.Daos;

import com.example.hospital.Model.Tablas.Adminis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
        String sql="DELETE FROM admin WHERE IdAdmin = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modificar(Adminis adminis) {
        String sql="UPDATE admin SET Nombre=? WHERE IdAdmin = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, adminis.getNombre());
            stmt.setString(2,adminis.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Adminis consultar(String id) {
        String sql = "SELECT * FROM admin WHERE IdAdmin = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Adminis adminis = new Adminis(rs.getString("IdAdmin"), rs.getString("Nombre"));
                return adminis;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<Adminis> listar() {
        String sql = "SELECT * FROM admin";
        ObservableList<Adminis> add= FXCollections.observableArrayList();
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
