package com.project.twittersimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.project.twittersimulation.App.scene;
import static com.project.twittersimulation.App.stage;

public class ShowProfile implements Initializable {


    public static String userName;
    private   AccountType accountType;
    private BusinessType businessType;


    @FXML
    private Label accountTypeLabel;

    @FXML
    private Label businessType1;

    @FXML
    private TableColumn<Posts , Integer> commentColumn;

    @FXML
    private TableColumn<Posts , String> contentColumn;

    @FXML
    private TableColumn<Posts , String> dateColumn;

    @FXML
    private ComboBox<Followers> followersCombo;

    @FXML
    private Label followersCount;

    @FXML
    private ComboBox<Followings> followingsCombo;

    @FXML
    private Label followingsCount;

    @FXML
    private TableColumn<Posts , String> imageColumn;

    @FXML
    private TableColumn<Posts , Integer> likeColumn;

    @FXML
    private TableColumn<Posts, Integer> numberColumn;

    @FXML
    private Label postCount;

    @FXML
    private TableView<Posts> postTable;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Label user;

    @FXML
    private Label wrongUser;

    @FXML
    void BackToHome(MouseEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage.setTitle("welcome " + Login.userName);
        scene.setRoot(root);
        stage.setScene(scene);
    }

    @FXML
    void ShowPosts(MouseEvent event) {

    }

    @FXML
    void showProfile(MouseEvent event) throws IOException {

        if (followingsCombo.getValue() != null){



            ShowProfile.userName = followingsCombo.getValue().getFollowingName();
            Pane root = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
            stage.setTitle(ShowProfile.userName + " profile");
            scene.setRoot(root);
            stage.setScene(scene);
        }
        else if (followersCombo.getValue() != null){
            ShowProfile.userName = followersCombo.getValue().getFollowerName();


            Pane root = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
            stage.setTitle(ShowProfile.userName + " profile");
            scene.setRoot(root);
            stage.setScene(scene);

        }
        else {
            wrongUser.setText("please select a user");
        }
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

    private ObservableList<Posts> PostsList (ArrayList<Posts> temp) {
        ObservableList<Posts> professors1 = FXCollections.observableArrayList();
        professors1.addAll(temp);
        return professors1;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        wrongUser.setText("");
        Posts.allPost.clear();
        Followers.followersList.clear();
        Followings.followingsList.clear();

        user.setText(userName);

        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        likeColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));




        final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
        final String username = "root";
        final String Password = "Smok2003@";

        ArrayList<Followers> followersList = new ArrayList<>();
        ArrayList<Followings> followingsList = new ArrayList<>();

        try {


            Connection conn = DriverManager.getConnection(DB_url, username, Password);
            Statement statement0 = conn.createStatement();
            Statement statement = conn.createStatement();
            Statement statement1 = conn.createStatement();
            Statement statement2 = conn.createStatement();
            Statement statement3 = conn.createStatement();


            String sql0 = "SELECT * FROM personalInformation WHERE username = '" + userName + "'";
            String sql = "SELECT COUNT(sender) FROM posts WHERE sender = '" + userName + "'";
            String sql1 = "SELECT * FROM followings";
            String sql2 = "SELECT * FROM followers";
            String sql3 = "SELECT * FROM posts WHERE sender = '" + userName + "'";



            ResultSet resultSet0 = statement0.executeQuery(sql0);
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSet resultSet1 = statement1.executeQuery(sql1);
            ResultSet resultSet2 = statement2.executeQuery(sql2);
            ResultSet resultSet3 = statement3.executeQuery(sql3);


            if(resultSet0.next()){
                accountType = AccountType.valueOf(resultSet0.getString("accounttype"));
                accountTypeLabel.setText(String.valueOf(accountType));

                if (accountType.equals(AccountType.BusinessAccount)){
                    businessType = BusinessType.valueOf(resultSet0.getString("businessType"));
                    businessType1.setText(String.valueOf(businessType));
                }
                else {
                    businessType1.setText("");
                }

            }


            int temp =0 ;

            if (resultSet.next()){
                temp = resultSet.getInt(1);
                postCount.setText("post : " + resultSet.getInt(1));
            }
            else {
                System.out.println("ridi");
            }


            while (resultSet1.next()) {
                    if (resultSet1.getString(userName) != null) {
                        Followers follower = new Followers(resultSet1.getString(userName));

                        followersList.add(follower);
                        Followers.followersList.add(resultSet1.getString(userName));
                    }
                }


            while (resultSet2.next()) {
                if (resultSet2.getString(userName) != null) {
                    Followings following = new Followings(resultSet2.getString(userName));

                    followingsList.add(following);
                    Followings.followingsList.add(resultSet2.getString(userName));

                }
            }

            followersCombo.setItems(FollowersList(followersList));
            followingsCombo.setItems(FollowingsList(followingsList));
            followersCount.setText("followers : " + followersList.size());
            followingsCount.setText("followings : " + followingsList.size());



            while (resultSet3.next()){
                sql1 = "SELECT COUNT(*) FROM comments WHERE postId = " + resultSet3.getInt("id");
                sql2 = "SELECT COUNT(*) FROM likes WHERE postId = " + resultSet3.getInt("id");

                resultSet1 = statement1.executeQuery(sql1);
                resultSet2 = statement2.executeQuery(sql2);


                Posts posts = new Posts();
                posts.setNumber(temp);
                posts.setContent(resultSet3.getString("postContent"));
                posts.setDate("postDate");
                posts.setImage("Yes");
                if (resultSet1.next()){
                    posts.setComment(resultSet1.getInt(1));
                }
                if (resultSet2.next()){
                    posts.setLikes(resultSet2.getInt(1));
                }


                Posts.allPost.add(posts);


                temp--;
            }


            postTable.setItems(PostsList(Posts.allPost));


        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
