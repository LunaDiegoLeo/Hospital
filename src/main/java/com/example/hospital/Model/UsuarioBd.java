package com.example.hospital.Model;

import com.example.hospital.Model.Tablas.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioBd {
    private Connection connection;
    public UsuarioBd() throws Exception {
        this.connection = ConexioBD.getConnection();
    }
    public Usuario buscarUsuario(String usuario, String password){
        String sql="SELECT * FROM usuarios WHERE usuario=?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String pass= rs.getString("password");
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] result = md.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();

                for (byte b : result) {
                    sb.append(String.format("%02x", b));
                }

                String npas= sb.toString();
                if(npas.equals(pass)){
                    return new Usuario(rs.getString("usuario"), null, rs.getString("idExterno"), rs.getInt("Tipo"));
                } else{
                    return null;
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public Usuario buscarId(String usuario, String password){
        String sql="SELECT * FROM usuarios WHERE idExterno=?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String pass= rs.getString("password");
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] result = md.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();

                for (byte b : result) {
                    sb.append(String.format("%02x", b));
                }

                String npas= sb.toString();
                if(npas.equals(pass)){
                    return new Usuario(rs.getString("usuario"), null, rs.getString("idExterno"), rs.getInt("Tipo"));
                } else{
                    return null;
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public boolean cambiarContraseña(String idex, String password){
        String sql="UPDATE usuarios SET password=? WHERE idExterno=?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] result = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : result) {
                sb.append(String.format("%02x", b));
            }
            stmt.setString(1, sb.toString());
            stmt.setString(2, idex);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }

    }
}
