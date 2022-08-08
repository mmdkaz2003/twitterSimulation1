package com.project.twittersimulation;



import com.project.twittersimulation.model.BusinessAccount;
import com.project.twittersimulation.model.Followers;
import com.project.twittersimulation.model.Followings;
import com.project.twittersimulation.model.NormalAccount;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    public static String userName;
    public static String name;
    public static String birthDay;
    public static String password;
    public static BusinessAccount businessAccount;
    public static NormalAccount normalAccount;
    public static String theme;
    public static String security;
    public static ArrayList<Followers> followersList = new ArrayList<>();
    public static ArrayList<Followings> followingsList = new ArrayList<>();


    public void home(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        App.scene.setRoot(pane);
    }

    public void viewProfile(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        if (businessAccount == null) {
            pane = FXMLLoader.load(getClass().getResource("profile.fxml"));
        } else {
            pane = FXMLLoader.load(getClass().getResource("businessProfile.fxml"));

        }
        App.scene.setRoot(pane);
        App.stage.setTitle("view profile");
    }

    public void explorePost(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("ExplorePost.fxml"));
        App.scene.setRoot(pane);
    }

    public void exploreUser(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("ExploreUser.fxml"));
        App.scene.setRoot(pane);
    }

    public void createPost(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
        App.scene.setRoot(pane);
    }

    public void recommendsdUser(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("userRecommend.fxml"));
        App.scene.setRoot(pane);
        App.stage.setTitle("recommended user");
    }

    public void recommendedPost(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("PostRecom.fxml"));
        App.scene.setRoot(pane);
    }

    public void chat(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        App.scene.setRoot(pane);
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        App.scene.getStylesheets().clear();
        String css = this.getClass().getResource("/CSS/buttonColor.css").toExternalForm();
        App.scene.getStylesheets().add(css);

        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        App.scene.setRoot(pane);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        followersList.clear();
        followingsList.clear();

        final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
        final String username = "root";
        final String Password = "Smok2003@";


        try {

            Connection conn = DriverManager.getConnection(DB_url, username, Password);
            Statement statement1 = conn.createStatement();
            Statement statement2 = conn.createStatement();


            String sql2 = "SELECT " + MenuController.userName + " FROM followings";
            String sql1 = "SELECT " + MenuController.userName + " FROM followers";

            ResultSet resultSet1 = statement1.executeQuery(sql1);
            ResultSet resultSet2 = statement2.executeQuery(sql2);

            while (resultSet1.next()) {
                    if (resultSet1.getString(MenuController.userName) != null) {
                        Followers follower = new Followers(resultSet1.getString(MenuController.userName));

                        followersList.add(follower);
                        Followers.followersList.add(resultSet1.getString(MenuController.userName));
                    }
                }

            while (resultSet2.next()) {
                    if (resultSet2.getString(MenuController.userName) != null) {
                        Followings following = new Followings(resultSet2.getString(MenuController.userName));

                        followingsList.add(following);
                        Followings.followingsList.add(resultSet2.getString(MenuController.userName));
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
