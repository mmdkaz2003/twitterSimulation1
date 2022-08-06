package com.project.twittersimulation;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class Login {

    public static String userName;
    public static String password;
    public static String birthDay;
    public static String name;
    public static AccountType accountType;
    public static BusinessType businessType;


    @FXML
    private Label wrongUser;

    @FXML
    private Button forgotButton;

    @FXML
    private Label forgotLabel;

    @FXML
    private Label accountLabel;

    @FXML
    private Button accountButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameLabel;



    public void Login(ActionEvent event) {


        final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
        final String username = "root";
        final String Password = "Smok2003@";
        try {

            Connection conn = DriverManager.getConnection(DB_url, username, Password);
            Statement statement = conn.createStatement();

            String sql = "SELECT * FROM personalInformation" ;
            ResultSet resultSet = statement.executeQuery(sql);

            String userName = usernameField.getText().toString().trim();
            String passWord = passwordField.getText().toString().trim();


            if (userName.isEmpty() || passWord.isEmpty()){
                wrongUser.setText("please enter your data");
            }


            else {
                boolean exist = false;
                while (resultSet.next()) {
                    //boolean exist = false;

                    String temp1 = resultSet.getString("username").trim();
                    String temp2 = resultSet.getString("password").trim();


                    if (temp1.equals(userName)) {
                        exist = true;
                        if (temp2.equals(passWord)) {

                            wrongUser.setText("Login successfully ");

                            Login.userName = userName;
                            password = passWord;
                            birthDay = resultSet.getString("birthday").trim();
                            accountType = AccountType.valueOf(resultSet.getString("accounttype").trim());
                            if (accountType.equals(AccountType.BusinessAccount)){
                                businessType = BusinessType.valueOf(resultSet.getString("businessType").trim());
                            }
                            GoToMenu(event);

                        } else {
                            wrongUser.setText("your password is wrong");
                        }
                        break;
                    }


                }

                if (!exist) {
                    wrongUser.setText("there is no user with this username");
                }
            }

        }

        catch(SQLException e){
            e.printStackTrace();
        } catch (IOException e) {

        }

    }


    public void Forgot(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
        App.stage.setTitle("forgot password");
        App.scene.setRoot(root);
    }

    public void GoToMenu(ActionEvent event) throws IOException{
        Pane root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        App.stage.setTitle("welcome " + usernameField.getText().toString().trim());
        App.stage.setHeight(800);
        App.stage.setWidth(1200);
        App.scene.setRoot(root);
        App.stage.setScene(App.scene);
    }



    public void Create(ActionEvent event) {
    }


}












