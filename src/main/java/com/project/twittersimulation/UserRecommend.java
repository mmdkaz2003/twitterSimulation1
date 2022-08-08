package com.project.twittersimulation;

import com.project.twittersimulation.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.project.twittersimulation.App.scene;
import static com.project.twittersimulation.App.stage;


public class UserRecommend extends MenuController implements Initializable {

    ArrayList<String> tempUsersName = new ArrayList<>();
    ArrayList<Integer> tempUsersScore = new ArrayList<>();

    ArrayList<String> usersName = new ArrayList<>();
    ArrayList<Integer> usersScore = new ArrayList<>();

    final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
    final String username = "root";
    final String Password = "Smok2003@";


    @FXML
    private TableColumn<User, String> accountColumn;

    @FXML
    private TableColumn<User , String> birthColumn;

    @FXML
    private TableColumn<User, String> businessColumn;

    @FXML
    private TableColumn<User, Integer> numberColumn;

    @FXML
    private Button showProf;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private Label usernameLabel;

    @FXML
    private TableView<User> usersTable;


    @FXML
    void ShowProfile(MouseEvent event) throws IOException {
        User user = usersTable.getSelectionModel().getSelectedItem();


        if (user != null){
            ShowProfile.userName = user.getUsername();

            Pane root = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
            stage.setTitle(ShowProfile.userName + " profile");
            scene.setRoot(root);

        }
    }



    private ObservableList<User> UsersList (ArrayList<User> temp) {
        ObservableList<User> professors1 = FXCollections.observableArrayList();
        professors1.addAll(temp);
        return professors1;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User.allRecommendedUSer.clear();

        usernameLabel.setText("the recommended users for " + MenuController.userName);

        Rating();
        selectionSort(tempUsersScore);

        usersName = reverseStringArrayList(tempUsersName);
        usersScore = reverseIntegerArrayList(tempUsersScore);



        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        businessColumn.setCellValueFactory(new PropertyValueFactory<>("businessType"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDay"));


        int temp =1;
        for (String i : usersName){


            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement1 = conn.createStatement();
                String sql1 = "SELECT * FROM personalInformation WHERE username = '" + i + "'";
                ResultSet resultSet1 = statement1.executeQuery(sql1);


               if (resultSet1.next()){
                   User user;
                   AccountType account = AccountType.valueOf(resultSet1.getString("accounttype"));

                   BusinessType business;

                   if (account.equals(AccountType.BusinessAccount)){
                       business = BusinessType.valueOf(resultSet1.getString("businessType"));
                       user = new User(temp , i ,String.valueOf(account) , String.valueOf(business) , "-");
                   }
                   else {
                       String birth = resultSet1.getString("birthday");
                       user = new User(temp , i ,String.valueOf(account) , "-" , birth);
                   }
                   User.allRecommendedUSer.add(user);



               }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            temp++;
        }

        usersTable.setItems(UsersList(User.allRecommendedUSer));





    }




    private void Rating (){



        for (Followers j : MenuController.followersList){

            tempUsersName.add(j.getFollowerName());
            tempUsersScore.add(3);
        }

        for (Followings j : MenuController.followingsList) {
            String i = j.getFollowingName();


            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement1 = conn.createStatement();
                String sql1 = "SELECT * FROM followings";
                ResultSet resultSet1 = statement1.executeQuery(sql1);


                while (resultSet1.next()){
                    if (resultSet1.getString(i) != null && !Followings.followingsList.contains(resultSet1.getString(i)) && !resultSet1.getString(i).equals(userName)){
                        if (tempUsersName.contains(resultSet1.getString(i))){
                            tempUsersScore.set(tempUsersName.indexOf(resultSet1.getString(i)) , tempUsersScore.get(tempUsersName.indexOf(resultSet1.getString(i))) + 5) ;
                        }
                        else {
                            tempUsersName.add(resultSet1.getString(i));
                            tempUsersScore.add(5);
                        }
                    }
                }


                Statement statement2 = conn.createStatement();
                String sql2 = "SELECT * FROM followers";
                ResultSet resultSet2 = statement2.executeQuery(sql2);

                while (resultSet2.next()){
                    if (resultSet2.getString(i) != null && !Followings.followingsList.contains(resultSet2.getString(i)) && !resultSet2.getString(i).equals(userName)) {
                        if (tempUsersName.contains(resultSet2.getString(i))) {
                            tempUsersScore.set(tempUsersName.indexOf(resultSet2.getString(i)), tempUsersScore.get(tempUsersName.indexOf(resultSet2.getString(i))) + 4);
                        }
                        else {
                            tempUsersName.add(resultSet2.getString(i));
                            tempUsersScore.add(4);
                        }
                    }
                }


            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for (Followers j : MenuController.followersList) {
            String i = j.getFollowerName();
            System.out.println(i);


            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement1 = conn.createStatement();
                String sql1 = "SELECT "+ i +" FROM followings ";
                ResultSet resultSet1 = statement1.executeQuery(sql1);


                while (resultSet1.next()){
                    if (resultSet1.getString(i) != null  && !Followings.followingsList.contains(resultSet1.getString(i)) && !resultSet1.getString(i).equals(userName)){
                        System.out.println(resultSet1.getString(i));
                        if (tempUsersName.contains(resultSet1.getString(i))){
                            tempUsersScore.set(tempUsersName.indexOf(resultSet1.getString(i)) , tempUsersScore.get(tempUsersName.indexOf(resultSet1.getString(i))) + 2) ;
                        }
                        else {
                            tempUsersName.add(resultSet1.getString(i));
                            tempUsersScore.add(2);
                        }
                    }
                }


                Statement statement2 = conn.createStatement();
                String sql2 = "SELECT * FROM followers";
                ResultSet resultSet2 = statement2.executeQuery(sql2);

                while (resultSet2.next()){
                    if (resultSet2.getString(i) != null  && !Followings.followingsList.contains(resultSet2.getString(i)) && !resultSet2.getString(i).equals(userName)) {

                        if (tempUsersName.contains(resultSet2.getString(i))) {
                            tempUsersScore.set(tempUsersName.indexOf(resultSet2.getString(i)), tempUsersScore.get(tempUsersName.indexOf(resultSet2.getString(i))) + 1);
                        }
                        else {
                            tempUsersName.add(resultSet2.getString(i));
                            tempUsersScore.add(1);
                        }
                    }
                }


            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private void selectionSort(ArrayList<Integer> arr) {
        int pos;
        int temp1;
        String temp2;
        for (int i = 0; i < arr.size(); i++)
        {
            pos = i;
            for (int j = i+1; j < arr.size(); j++)
            {
                if (arr.get(j) < arr.get(pos))                  //find the index of the minimum element
                {
                    pos = j;
                }
            }

            temp1 = arr.get(pos);            //swap the current element with the minimum element
            arr.set(pos , arr.get(i));
            arr.set(i  ,temp1 );


            temp2 = tempUsersName.get(pos);            //swap the current element with the minimum element
            tempUsersName.set(pos , tempUsersName.get(i));
            tempUsersName.set(i  ,temp2 );


        }
    }

    public ArrayList<Integer> reverseIntegerArrayList(ArrayList<Integer> alist){
        // Arraylist for storing reversed elements
        ArrayList<Integer> revArrayList = new ArrayList<Integer>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }

        // Return the reversed arraylist
        return revArrayList;
    }

    public ArrayList<String> reverseStringArrayList(ArrayList<String> alist){
        // Arraylist for storing reversed elements
        ArrayList<String> revArrayList = new ArrayList<>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }

        // Return the reversed arraylist
        return revArrayList;
    }

}
