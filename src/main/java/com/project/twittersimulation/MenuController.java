package com.project.twittersimulation;



import com.project.twittersimulation.model.BusinessAccount;
import com.project.twittersimulation.model.NormalAccount;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    public static String userName;
    public static String name;
    public static String birthDay;
    public static String password;
    public static BusinessAccount businessAccount;
    public static NormalAccount normalAccount;


    public void home(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("Menu.fxml"));
        App.scene.setRoot(pane);
    }

    public void viewProfile(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        if (businessAccount == null) {
            pane = FXMLLoader.load(getClass().getResource("profile.fxml"));
        }
        else {
            pane = FXMLLoader.load(getClass().getResource("businessProfile.fxml"));

        }
        App.scene.setRoot(pane);
    }

    public void explorePost(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("ExplorePost.fxml"));
        App.scene.setRoot(pane);
    }

    public void exploreUser(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("ExploreUser.fxml"));
        App.scene.setRoot(pane);
    }

    public void createPost(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
        App.scene.setRoot(pane);
    }

    public void recommendsdUser(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("UserRecom.fxml"));
        App.scene.setRoot(pane);
    }

    public void recommendedPost(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("PostRecom.fxml"));
        App.scene.setRoot(pane);
    }

    public void chat(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("Chat.fxml"));
        App.scene.setRoot(pane);
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("Login.fxml"));
        App.scene.setRoot(pane);

    }
}
