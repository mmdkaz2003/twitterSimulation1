package com.project.twittersimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;


public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(600);

        Login.scene = scene;
        Login.stage = stage;


        stage.widthProperty().addListener((obs, oldVal, newVal) -> {

        });


//        StackPane root = new StackPane();
//        root.setStyle(
//                "-fx-background-image: url('1.jpg'); " +
//                        "-fx-background-size: cover;"
//        );
//        stage.setScene(new Scene(root));
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}