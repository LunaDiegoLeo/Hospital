package com.example.hospital.Config;

public class Config {
    private String URL = "jdbc:mysql://localhost:3306/hospitalh";
    private String USER = "root";
    private String PASSWORD = "";
    private static Config instance;
    public String getURL() {
        return URL;
    }
    public String getUser() {
        return USER;
    }
    public String getPassword() {
        return PASSWORD;
    }
    public void setURL(String uRL) {
        URL = uRL;
    }
    public void setUser(String user) {
        USER = user;
    }
    public void setPassword(String password) {
        PASSWORD = password;
    }


    private Config(String url, String user, String password) {
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }
    public static Config getIntance(String url, String user, String password) {
        if(instance == null) {
            instance = new Config(url, user, password);
            return instance;
        }
        return instance;
    }
}
