package com.project.twittersimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class App extends Application {

    public static Scene scene;
    public static Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(800);

        App.stage = stage;

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