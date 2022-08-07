package com.project.twittersimulation;

import com.project.twittersimulation.model.Followers;
import com.project.twittersimulation.model.Followings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserRecommend extends MenuController implements Initializable {

    ArrayList<String> usersName = new ArrayList<>();
    ArrayList<Integer> usersScore = new ArrayList<>();

    final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
    final String username = "root";
    final String Password = "Smok2003@";


    @FXML
    private TableColumn<?, ?> accountColumn;

    @FXML
    private TableColumn<?, ?> birthColumn;

    @FXML
    private TableColumn<?, ?> businessColumn;

    @FXML
    private TableColumn<?, ?> numberColumn;

    @FXML
    private Button showProf;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    private Label usernameLabel;

    @FXML
    private TableView<?> usersTable;


    @FXML
    void ShowProfile(MouseEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rating();
        selectionSort(usersScore);


    }




    private void Rating (){



        for (Followers j : MenuController.followersList){

            usersName.add(j.getFollowerName());
            usersScore.add(3);
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
                        if (usersName.contains(resultSet1.getString(i))){
                            usersScore.set(usersName.indexOf(resultSet1.getString(i)) , usersScore.get(usersName.indexOf(resultSet1.getString(i))) + 5) ;
                        }
                        else {
                            usersName.add(resultSet1.getString(i));
                            usersScore.add(5);
                        }
                    }
                }


                Statement statement2 = conn.createStatement();
                String sql2 = "SELECT * FROM followers";
                ResultSet resultSet2 = statement2.executeQuery(sql2);

                while (resultSet2.next()){
                    if (resultSet2.getString(i) != null && !Followings.followingsList.contains(resultSet2.getString(i)) && !resultSet2.getString(i).equals(userName)) {
                        if (usersName.contains(resultSet2.getString(i))) {
                            usersScore.set(usersName.indexOf(resultSet2.getString(i)), usersScore.get(usersName.indexOf(resultSet2.getString(i))) + 4);
                        }
                        else {
                            usersName.add(resultSet2.getString(i));
                            usersScore.add(4);
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


            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement1 = conn.createStatement();
                String sql1 = "SELECT * FROM followings";
                ResultSet resultSet1 = statement1.executeQuery(sql1);


                while (resultSet1.next()){
                    if (resultSet1.getString(i) != null  && !Followers.followersList.contains(resultSet1.getString(i)) && !resultSet1.getString(i).equals(userName)){
                        if (usersName.contains(resultSet1.getString(i))){
                            usersScore.set(usersName.indexOf(resultSet1.getString(i)) , usersScore.get(usersName.indexOf(resultSet1.getString(i))) + 2) ;
                        }
                        else {
                            usersName.add(resultSet1.getString(i));
                            usersScore.add(2);
                        }
                    }
                }


                Statement statement2 = conn.createStatement();
                String sql2 = "SELECT * FROM followers";
                ResultSet resultSet2 = statement2.executeQuery(sql2);

                while (resultSet2.next()){
                    if (resultSet2.getString(i) != null  && !Followers.followersList.contains(resultSet2.getString(i)) && !resultSet2.getString(i).equals(userName)) {

                        if (usersName.contains(resultSet2.getString(i))) {
                            usersScore.set(usersName.indexOf(resultSet2.getString(i)), usersScore.get(usersName.indexOf(resultSet2.getString(i))) + 1);
                        }
                        else {
                            usersName.add(resultSet2.getString(i));
                            usersScore.add(1);
                        }
                    }
                }


            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private void selectionSort(ArrayList<Integer> arr)
    {
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


            temp2 = usersName.get(pos);            //swap the current element with the minimum element
            usersName.set(pos , usersName.get(i));
            usersName.set(i  ,temp2 );


        }
    }
}
