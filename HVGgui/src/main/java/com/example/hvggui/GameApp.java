package com.example.hvggui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApp extends Application {
    SetUp game;

    public GameApp(){}

    public void start(Stage stage) throws IOException {
        FXMLLoader x = new FXMLLoader(SetUp.class.getResource("/com.example.hvggui/gaming-info.fxml"));
        Scene scene = new Scene(x.load());
        stage.setTitle("Humans Versus Goblins");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}