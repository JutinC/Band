/*
Justin Choi
CSP Period 6
Schenk
11 April 2024
Band peoople
 */
package com.example.band;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BandApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("band.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 772, 400);
        scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

        stage.setTitle("Band");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
