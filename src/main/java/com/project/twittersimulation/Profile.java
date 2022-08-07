package com.project.twittersimulation;

import com.project.twittersimulation.model.Followers;
import com.project.twittersimulation.model.Followings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.project.twittersimulation.App.scene;
import static com.project.twittersimulation.App.stage;


public class Profile extends MenuController implements Initializable {





    @FXML
    private Button SeePosts;

    @FXML
    private Label birth;

    @FXML
    private TableColumn<Followers, String> followersColumn;

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
    void showSelectedProf(MouseEvent event) throws IOException {
        Followings following = followingsTable.getSelectionModel().getSelectedItem();
        Followers followers = followersTable.getSelectionModel().getSelectedItem();


        if (following != null){
            ShowProfile.userName = following.getFollowingName();

            Pane root = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
            stage.setTitle(ShowProfile.userName + " profile");
            scene.setRoot(root);

        }
        else if (followers != null){
            ShowProfile.userName = followers.getFollowerName();
            Pane pane = null;
            pane = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
            stage.setTitle(ShowProfile.userName + " profile");
            App.scene.setRoot(pane);

        }


    }

    @FXML
    public void SeeMyPost(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("myPosts.fxml"));
        stage.setTitle("my posts");
        scene.setRoot(pane);
    }

    @FXML
    public void Edit(MouseEvent mouseEvent) throws IOException {
        Pane pane = null;
        pane = FXMLLoader.load(getClass().getResource("editProfile.fxml"));
        stage.setTitle("edit profile");
        scene.setRoot(pane);
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


        user.setText(MenuController.userName);
        birth.setText("birth day : " + MenuController.birthDay);




        followersColumn.setCellValueFactory(new PropertyValueFactory<>("followerName"));
        followingsColumn.setCellValueFactory(new PropertyValueFactory<>("followingName"));


        followersTable.setItems(FollowersList(followersList));
        followingsTable.setItems(FollowingsList(followingsList));


        final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
        final String username = "root";
        final String Password = "Smok2003@";


        try {


            Connection conn = DriverManager.getConnection(DB_url, username, Password);
            Statement statement = conn.createStatement();

            String sql = "SELECT COUNT(sender) FROM posts WHERE sender = '" + MenuController.userName + "'";

            ResultSet resultSet = statement.executeQuery(sql);



            if (resultSet.next()){
                postCount.setText("number of post : " + resultSet.getInt(1));
            }
            else {
                System.out.println("ridi");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
