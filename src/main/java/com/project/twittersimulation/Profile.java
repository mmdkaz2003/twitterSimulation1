package com.project.twittersimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Profile implements Initializable {


    @FXML
    private Button SeePosts;

    @FXML
    private Label birth;

    @FXML
    private TableColumn<Followers , String> followersColumn;

    @FXML
    private TableView<Followers> followersTable;

    @FXML
    private TableColumn<Followings, String> followingsColumn;

    @FXML
    private TableView<Followings> followingsTable;

    @FXML
    private Label postCount;

    @FXML
    private Button showProfile;

    @FXML
    private Label user;

    @FXML
    void Edit(ActionEvent event) {

    }


    @FXML
    public void chat(MouseEvent mouseEvent) {
    }

    @FXML
    public void recommendedPost(MouseEvent mouseEvent) {
    }

    @FXML
    public void recommendsUser(MouseEvent mouseEvent) {
    }

    @FXML
    public void createPost(MouseEvent mouseEvent) {
    }

    @FXML
    public void exploreUser(MouseEvent mouseEvent) {
    }

    @FXML
    public void explorePost(MouseEvent mouseEvent) {
    }

    @FXML
    public void viewProfile(MouseEvent mouseEvent) {

    }

    @FXML
    public void followingsPost(MouseEvent mouseEvent) {
    }

    @FXML
    public void logout(MouseEvent mouseEvent) {
    }


    private ObservableList<Followers> FollowersList (ArrayList<Followers> temp) {
        ObservableList<Followers> professors1 = FXCollections.observableArrayList();
        professors1.addAll(temp);
        return professors1;
    }

    private ObservableList<Followings> FollowingsList (ArrayList<Followings> temp) {
        ObservableList<Followings> professors1 = FXCollections.observableArrayList();
        professors1.addAll(temp);
        return professors1;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        user.setText(Login.userName);



        followersColumn.setCellValueFactory(new PropertyValueFactory<>("followerName"));
        followingsColumn.setCellValueFactory(new PropertyValueFactory<>("followingName"));



        final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
        final String username = "root";
        final String Password = "Smok2003@";

        ArrayList<Followers> followersList = new ArrayList<>();
        ArrayList<Followings> followingsList = new ArrayList<>();

        try {


            Connection conn = DriverManager.getConnection(DB_url, username, Password);
            Statement statement1 = conn.createStatement();
            Statement statement2 = conn.createStatement();


            String sql1 = "SELECT " + Login.userName + " FROM followings";
            String sql2 = "SELECT " + Login.userName + " FROM followers";

            ResultSet resultSet1 = statement1.executeQuery(sql1);
            ResultSet resultSet2 = statement2.executeQuery(sql2);

            while (resultSet1.next()) {
                if (resultSet1.getString(Login.userName) != null) {
                    Followers follower = new Followers(resultSet1.getString(Login.userName));

                    followersList.add(follower);
                    Followers.followersList.add(resultSet1.getString(Login.userName));
                    System.out.println(resultSet1.getString(Login.userName));
                }
            }
            while (resultSet2.next()) {
                if (resultSet2.getString(Login.userName) != null) {
                    Followings following = new Followings(resultSet2.getString(Login.userName));

                    followingsList.add(following);
                    Followings.followingsList.add(resultSet2.getString(Login.userName));
                    System.out.println(resultSet2.getString(Login.userName));
                }
            }

            followersTable.setItems(FollowersList(followersList));
            followingsTable.setItems(FollowingsList(followingsList));


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
