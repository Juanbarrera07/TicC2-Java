package com.example.mintic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class reto3 extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(reto3.class.getResource("CalidadAgua.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Reto 3 Calidad del Agua");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}