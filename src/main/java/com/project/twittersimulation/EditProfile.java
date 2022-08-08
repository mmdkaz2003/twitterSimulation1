package com.project.twittersimulation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.project.twittersimulation.App.scene;
import static com.project.twittersimulation.App.stage;

public class EditProfile implements Initializable {

    final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
    final String username = "root";
    final String Password = "Smok2003@";


    @FXML
    private TextField passwordConfirmField;

    @FXML
    private Label passwordConfirmLabel;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField securityNewField;

    @FXML
    private Label securityNewLabel;

    @FXML
    private TextField securityOldField;

    @FXML
    private Label securityOldLabel;

    @FXML
    private ComboBox<String> themeCombo;

    @FXML
    private Label wrongPassword;

    @FXML
    private Label wrongQ;

    @FXML
    private Label wrongTheme;

    @FXML
    void BackViewProfile(MouseEvent event) throws IOException {
        Pane pane = null;
        if (MenuController.businessAccount == null) {
            pane = FXMLLoader.load(getClass().getResource("profile.fxml"));
        } else {
            pane = FXMLLoader.load(getClass().getResource("businessProfile.fxml"));

        }
        stage.setTitle("view profile");
        scene.setRoot(pane);
    }

    @FXML
    void ChangePass(MouseEvent event) {
        wrongTheme.setText("");
        wrongQ.setText("");


        String password1 = passwordField.getText().toString().trim();
        String confirm1 = passwordConfirmField.getText().toString().trim();


        if (password1.isEmpty() || confirm1.isEmpty()) {
            wrongPassword.setText("please enter your data");
        }
        else if (!format(password1)){
            wrongPassword.setText("wrong password format");
        }
        else if (!password1.equals(confirm1)){
            wrongPassword.setText("your password and it's confirmation don't match");
        }

        else {
            try {

                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement = conn.createStatement();

                String sql = "UPDATE personalInformation SET password = '" + password1 + "' WHERE username = '" + MenuController.userName +"'";
                int resultSet = statement.executeUpdate(sql);


                if (resultSet == 1) {
                    wrongPassword.setText("your password changed successfully");
                    MenuController.password = password1;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    void ChangeSecurity(MouseEvent event) {
        wrongPassword.setText("");
        wrongTheme.setText("");
        String old = securityOldField.getText().toString().trim();
        String newQ = securityNewField.getText().toString().trim();



        if (old.isEmpty() || newQ.isEmpty()) {
            wrongQ.setText("please enter your data");
        }
        else if (!old.equals(MenuController.security)){
            wrongQ.setText("wrong old security question answer");
        }

        else{
            try {

                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement = conn.createStatement();

                String sql = "UPDATE personalInformation SET securityQ = '" + newQ + "' WHERE username = '" + MenuController.userName +"'";
                int resultSet = statement.executeUpdate(sql);


                if (resultSet == 1) {
                    wrongQ.setText("your security question answer changed successfully");
                    MenuController.security = newQ;
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void changeTheme(MouseEvent event) {
        wrongQ.setText("");
        wrongPassword.setText("");

        String temp = themeCombo.getValue();
        if (temp.isEmpty()){
            wrongTheme.setText("please pick a theme");
        }
        else if (temp.equals("classic mode")) {
            App.scene.getStylesheets().clear();
            String css = this.getClass().getResource("/CSS/classicMode.css").toExternalForm();
            App.scene.getStylesheets().add(css);


            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement1 = conn.createStatement();

                String sql1 = "UPDATE personalInformation SET theme = '" + temp + "' WHERE username = '" + MenuController.userName + "'";
                MenuController.theme = temp;

                int resultSet1 = statement1.executeUpdate(sql1);

                if (resultSet1 == 1){
                    wrongTheme.setText("done:)");
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if (temp.equals("dark mode")){
            App.scene.getStylesheets().clear();
            String css = this.getClass().getResource("/CSS/darkMode.css").toExternalForm();
            App.scene.getStylesheets().add(css);

            try {
                Connection conn = DriverManager.getConnection(DB_url, username, Password);
                Statement statement1 = conn.createStatement();

                String sql1 = "UPDATE personalInformation SET theme = '" + temp + "' WHERE username = '" + MenuController.userName + "'";
                MenuController.theme = temp;

                int resultSet1 = statement1.executeUpdate(sql1);

                if (resultSet1 == 1){
                    wrongTheme.setText("done:)");
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        themeCombo.getItems().addAll("classic mode" , "dark mode");
    }

    private boolean format(String password){
        if (!password.contains("#") && !password.contains("\\") && !password.contains("@") && !password.contains("!") && !password.contains("%") && !password.contains("$") && !password.contains("&")) {
            return false;
        }

        else if (password.length() < 8 || password.length() > 16) {
            return false;
        }

        else {
            boolean number = false;
            boolean letter = false;
            for (int i = 0; i < password.length(); i++) {
                char temp = password.charAt(i);
                if ((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)) {
                    letter = true;
                } else if ((temp >= 48 && temp <= 57)) {
                    number = true;
                }
                if (letter && number) {
                    return true;
                }
            }
            return false;
        }
    }

}

