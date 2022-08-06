package com.project.twittersimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.project.twittersimulation.App.stage;
import static com.project.twittersimulation.App.scene;



public class Menu {


    
    public void logout(MouseEvent mouseEvent) {
    }

    public void chat(MouseEvent mouseEvent) {
    }

    public void recommendedPost(MouseEvent mouseEvent) {
    }

    public void recommendsUser(MouseEvent mouseEvent) {
    }

    public void createPost(MouseEvent mouseEvent) {
    }

    public void exploreUser(MouseEvent mouseEvent) {
    }

    public void explorePost(MouseEvent mouseEvent) {
    }

    public void viewProfile(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        stage.setTitle("see your profile");
        scene.setRoot(root);

    }

    public void followingsPost(MouseEvent mouseEvent) {
    }
}
