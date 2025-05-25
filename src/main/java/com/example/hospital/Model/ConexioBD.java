package com.example.hospital.Model;

import com.example.hospital.Config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioBD {
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalh";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Config config;
    public static Connection connection;

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            config = Config.getIntance(URL, USER, PASSWORD);
            if (config != null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(config.getURL(), config.getUser(), config.getPassword());
            } else {
                throw new Exception("Error al conectar con la base de datos");
            }
        }
        return connection;
    }


    public static void closeConnection() {
        if (connection != null) {
            try {
                config.setURL(null);
                config.setUser(null);
                config.setPassword(null);
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
