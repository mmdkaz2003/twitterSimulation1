package com.project.twittersimulation;

import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserRecommend extends MenuController implements Initializable {

    ArrayList<String> tempUsersName = new ArrayList<>();
    ArrayList<Integer> tempUsersScore = new ArrayList<>();

    final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
    final String username = "root";
    final String Password = "Smok2003@";



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rating();;
        selectionSort(tempUsersScore);


    }




    private void Rating (){

        ArrayList<String> followers = new ArrayList<>();
        ArrayList<String> followings = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_url, username, Password);
            Statement statement1 = conn.createStatement();
            String sql1 = "SELECT * FROM followings";
            ResultSet resultSet1 = statement1.executeQuery(sql1);

            while (resultSet1.next()){
                if (resultSet1.getString(userName) != null){
                    followings.add(resultSet1.getString(userName));
                }
            }


            Statement statement2 = conn.createStatement();
            String sql2 = "SELECT * FROM followers";
            ResultSet resultSet2 = statement2.executeQuery(sql2);

            while (resultSet2.next()){
                if (resultSet2.getString(userName) != null){
                    followers.add(resultSet2.getString(userName));
                    tempUsersName.add(resultSet2.getString(userName));
                    tempUsersScore.add(3);
                }
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        for (String i : followings) {

            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement1 = conn.createStatement();
                String sql1 = "SELECT * FROM followings";
                ResultSet resultSet1 = statement1.executeQuery(sql1);


                while (resultSet1.next()){
                    if (resultSet1.getString(i) != null && !followings.contains(resultSet1.getString(i)) && !resultSet1.getString(i).equals(userName)){
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
                    if (resultSet2.getString(i) != null && !followings.contains(resultSet2.getString(i)) && !resultSet2.getString(i).equals(userName)) {
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

        for (String i : followers) {

            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement1 = conn.createStatement();
                String sql1 = "SELECT * FROM followings";
                ResultSet resultSet1 = statement1.executeQuery(sql1);


                while (resultSet1.next()){
                    if (resultSet1.getString(i) != null  && !followings.contains(resultSet1.getString(i)) && !resultSet1.getString(i).equals(userName)){
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
                    if (resultSet2.getString(i) != null  && !followings.contains(resultSet2.getString(i)) && !resultSet2.getString(i).equals(userName)) {

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


            temp2 = tempUsersName.get(pos);            //swap the current element with the minimum element
            tempUsersName.set(pos , tempUsersName.get(i));
            tempUsersName.set(i  ,temp2 );


        }
    }
}
