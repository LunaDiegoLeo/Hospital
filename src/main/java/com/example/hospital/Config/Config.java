package com.example.hospital.Config;

public class Config {
    private String URL;
    private String USER;
    private String PASSWORD;
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


    private Config() {
        this.URL = "";
        this.USER = "";
        this.PASSWORD ="";
    }
    public static Config getIntance() {
        if(instance == null) {
            instance = new Config();
            return instance;
        }
        return instance;
    }
}
