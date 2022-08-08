package com.project.twittersimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    //    public static boolean light
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        scene = new Scene(pane, 1200, 800);
        stage.setTitle("Shwitter");

        String css = this.getClass().getResource("/CSS/buttonColor.css").toExternalForm();
        App.scene.getStylesheets().add(css);


        stage.setScene(scene);
        App.stage = stage;
        stage.show();
    }


}