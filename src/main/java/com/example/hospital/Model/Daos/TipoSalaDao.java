package com.example.hospital.Model.Daos;

import javafx.collections.ObservableList;
import com.example.hospital.Model.Tablas.TipoSala;
import java.sql.Connection;

public class TipoSalaDao extends Dao<TipoSala> {
    public TipoSalaDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insertar(TipoSala tipoSalaDao) {
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return false;
    }

    @Override
    public boolean modificar(TipoSala tipoSalaDao) {
        return false;
    }

    @Override
    public TipoSala consultar(String id) {
        return null;
    }

    @Override
    public ObservableList<TipoSala> listar() {
        ObservableList<TipoSala> tiposSala = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT TipoSala, descripcion FROM TipoSala";
        try (var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int idTipoSala = resultSet.getInt("TipoSala");
                String descripcion = resultSet.getString("descripcion");
                TipoSala tipoSala = new TipoSala(idTipoSala,descripcion);
                tiposSala.add(tipoSala);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tiposSala;
    }
}
