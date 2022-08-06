package com.project.twittersimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class Menu {

    private Stage stage ;
    private  Scene scene;
    
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
        Profile profile = new Profile();
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("see your profile");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void followingsPost(MouseEvent mouseEvent) {
    }
}
