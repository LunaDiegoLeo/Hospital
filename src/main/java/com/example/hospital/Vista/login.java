package com.example.hospital.Vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(login.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 470);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch();
    }
}