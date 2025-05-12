package com.example.hospital.Model.Admin;

import com.example.hospital.Model.Dao;
import com.example.hospital.Model.Usuario;
import javafx.collections.ObservableList;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDao extends Dao<Usuario> {
    public UsuarioDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(Usuario usuario) {
        String sql="INSERT INTO usuarios (usuario, password, idExterno, Tipo) VALUES (?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] result = md.digest(usuario.getPassword().getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(String.format("%02x", b));
            }

            String npas= sb.toString();
            stmt.setString(1,usuario.getUsuario());
            stmt.setString(2,npas);
            stmt.setString(3,usuario.getIdEx());
            stmt.setInt(4,usuario.getTipo());
            stmt.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql="DELETE FROM usuarios WHERE idExterno = ?";
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
    public boolean modificar(Usuario usuario) {
        return false;
    }

    @Override
    public Usuario consultar(String id) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Usuario usuario = new Usuario(rs.getString("usuario"), null, rs.getString("idExterno"), rs.getInt("Tipo"));
                return usuario;
            } else{
                return null;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Usuario> listar() {
        return null;
    }


}
