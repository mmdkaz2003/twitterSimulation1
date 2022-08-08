package com.project.twittersimulation;

import com.project.twittersimulation.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.project.twittersimulation.App.scene;
import static com.project.twittersimulation.App.stage;

public class ShowProfile implements Initializable {


    public static String userName;

    private AccountType accountType;
    private BusinessType businessType;
    private boolean follow;

    private ArrayList<Followers> followersList1 = new ArrayList<>();
    private ArrayList<Followings> followingsList1 = new ArrayList<>();



    final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
    final String username = "root";
    final String Password = "Smok2003@";



    @FXML
    private Label accountTypeLabel;

    @FXML
    private Label businessType1;

    @FXML
    private TableColumn<Posts, Integer> commentColumn;

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
    public Button followButton;

    @FXML
    public Label showPostLabel;

    @FXML
    public Label followLabel;


    @FXML
    void BackToHome(MouseEvent event) throws IOException {

        Pane root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage.setTitle("welcome " + MenuController.userName);
        scene.setRoot(root);
        stage.setScene(scene);
    }

    @FXML
    void ShowPosts(MouseEvent event) {
        wrongUser.setText("");
        followLabel.setText("");

    }

    @FXML
    void showProfile(MouseEvent event) throws IOException {
        showPostLabel.setText("");
        followLabel.setText("");

        if (followingsCombo.getValue() != null){

            if (!followingsCombo.getValue().getFollowingName().trim().equals(MenuController.userName)) {
                ShowProfile.userName = followingsCombo.getValue().getFollowingName().trim();


                Pane root = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
                stage.setTitle(ShowProfile.userName + " profile");
                scene.setRoot(root);
            }
            else {
                Pane root = FXMLLoader.load(getClass().getResource("profile.fxml"));
                stage.setTitle(ShowProfile.userName + " profile");
                scene.setRoot(root);
            }

        }

        else if (followersCombo.getValue() != null){

            if (!followersCombo.getValue().getFollowerName().trim().equals(MenuController.userName)) {

                ShowProfile.userName = followersCombo.getValue().getFollowerName();


                Pane root = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
                stage.setTitle(ShowProfile.userName + " profile");
                scene.setRoot(root);
            }
            else {
                Pane root = FXMLLoader.load(getClass().getResource("profile.fxml"));
                stage.setTitle(ShowProfile.userName + " profile");
                scene.setRoot(root);
            }

        }
        else {
            wrongUser.setText("please select a user");
        }
    }

    @FXML
    public void followOrUnFollow(MouseEvent mouseEvent) {
        wrongUser.setText("");
        showPostLabel.setText("");

        if (!follow){
            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement = conn.createStatement();
                String followingSql = "INSERT INTO followers ("+userName+") VALUES ('"+MenuController.userName+"')";
                String followerSql = "INSERT INTO followings ("+MenuController.userName+") VALUES ('"+userName+"')";


                int b =statement.executeUpdate(followingSql);
                int a = statement.executeUpdate(followerSql);
                if(a==1&&b==1){

                    Followers followers = new Followers(MenuController.userName);
                    followersList1.add(followers);

                    Followings followings1 = new Followings(userName);

                    MenuController.followingsList.add(followings1);



                    followersCombo.setItems(FollowersList(followersList1));

                    followersCount.setText("followers : " + followersList1.size());

                    followLabel.setText("follow successfully :)");
                    follow = true;
                    followButton.setText("unfollow");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        else {
            try {
                System.out.println("ajab2");
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement followingStatement = conn.createStatement();
                Statement followerStatement=conn.createStatement();
                String followingSql = "DELETE FROM followers WHERE "+userName+" LIKE '"+MenuController.userName+"'";
                String followerSql = "DELETE FROM followings WHERE "+ MenuController.userName + " LIKE '" + userName+"'";
                int a=followingStatement.executeUpdate(followingSql);
                int b=followerStatement.executeUpdate(followerSql);
                if(a==1 && b==1){

                    System.out.println("ajab3");
                    int temp =0;
                    for (Followers i : followersList1){
                        if (i.getFollowerName().equals(MenuController.userName)){
                            temp = followersList1.indexOf(i);
                        }
                    }

                    int temp1 =0;
                    for (Followings i : MenuController.followingsList){
                        if (i.getFollowingName().equals(userName)){
                            temp1 = MenuController.followingsList.indexOf(i);
                        }
                    }

                    followersList1.remove(temp);
                    MenuController.followingsList.remove(temp1);


                    followersCombo.setItems(FollowersList(followersList1));

                    followersCount.setText("followers : " + followersList1.size());

                    followLabel.setText("unfollow successfully :)");
                    follow = false;
                    followButton.setText("follow");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
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
        followLabel.setText("");
        showPostLabel.setText("");


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







        try {


            Connection conn = DriverManager.getConnection(DB_url, username, Password);
            Statement statement0 = conn.createStatement();
            Statement statement = conn.createStatement();
            Statement statement1 = conn.createStatement();
            Statement statement2 = conn.createStatement();
            Statement statement3 = conn.createStatement();


            String sql0 = "SELECT * FROM personalInformation WHERE username = '" + userName + "'";
            String sql = "SELECT COUNT(sender) FROM posts WHERE sender = '" + userName + "'";
            String sql1 = "SELECT "  + ShowProfile.userName + " FROM followers";
            String sql2 = "SELECT "  + ShowProfile.userName + " FROM followings";
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
                String temp1 = resultSet1.getString(userName);
                if (temp1 != null) {
                        Followers follower = new Followers(temp1.trim());

                        followersList1.add(follower);
                    }
                }


            while (resultSet2.next()) {
                String temp2 = resultSet2.getString(userName);
                if (temp2 != null) {
                    Followings following = new Followings(temp2.trim());

                    followingsList1.add(following);

                }
            }

            followersCombo.setItems(FollowersList(followersList1));
            followingsCombo.setItems(FollowingsList(followingsList1));
            followersCount.setText("followers : " + followersList1.size());
            followingsCount.setText("followings : " + followingsList1.size());



            while (resultSet3.next()){
                sql1 = "SELECT COUNT(*) FROM comments WHERE postId = " + resultSet3.getInt("id");
                sql2 = "SELECT COUNT(*) FROM likes WHERE postId = " + resultSet3.getInt("id");

                resultSet1 = statement1.executeQuery(sql1);
                resultSet2 = statement2.executeQuery(sql2);


                Posts posts = new Posts();
                posts.setNumber(temp);
                posts.setContent(resultSet3.getString("postContent"));
                posts.setDate(resultSet3.getString("postDate"));
                if (resultSet3.getString("imagePath") == null) {
                    posts.setImage("No");
                }
                else {
                    posts.setImage("Yes");
                }
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

        boolean exist = false;
        for (Followers i : followersList1) {
            System.out.println(userName);
            System.out.println(i.getFollowerName().trim());
            System.out.println("****************");
            if (i.getFollowerName().equals(MenuController.userName)) {
                followButton.setText("unfollow");
                follow = true;
                exist = true;
                System.out.println("ajab");
                break;
            }
        }
        if (!exist){
            followButton.setText("follow");
            follow = false;
        }


    }


}
