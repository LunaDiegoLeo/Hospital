package com.example.hospital.Model;

import com.example.hospital.Config.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioBD {
    private static String URL = "";
    private static String USER = "";
    private static String PASSWORD = "";
    private static Config config;
    public static Connection connection;

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Reader fr;
            BufferedReader br;
            fr = new FileReader("C:\\Users\\Sistemas\\Documents\\git\\Hospital\\src\\main\\java\\com\\example\\hospital\\Config\\Bd.txt");
            br = new BufferedReader(fr);
            URL=br.readLine();
            USER=br.readLine();
            PASSWORD=br.readLine();
            config = Config.getIntance();
            if (config != null) {
                config.setURL(URL);
                config.setUser(USER);
                config.setPassword(PASSWORD);
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
