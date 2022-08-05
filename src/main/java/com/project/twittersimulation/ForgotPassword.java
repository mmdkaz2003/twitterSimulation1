package com.project.twittersimulation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ForgotPassword {

    private Stage stage;
    private Scene scene;


    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField securityField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label wrongSecurity;

    @FXML
    void BackToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("welcome to rouzif 25 ");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ChangePassword(ActionEvent event ) {

        final String DB_url = "jdbc:mysql://localhost/users?serverTimezone=UTC";
        final String username = "root";
        final String Password = "Smok2003@";
        try {

            Connection conn = DriverManager.getConnection(DB_url, username, Password);
            Statement statement = conn.createStatement();

            String sql = "SELECT username,securityQ FROM personalInformation";
            ResultSet resultSet = statement.executeQuery(sql);

            String userName = usernameField.getText().toString().trim();
            String securityQ = securityField.getText().toString().trim();


            if (userName.isEmpty() || securityQ.isEmpty()){
                wrongSecurity.setText("please enter your data");
            }


            else {
                boolean exist = false;
                while (resultSet.next()) {


                    String temp1 = resultSet.getString("username").trim();
                    String temp2 = resultSet.getString("securityQ").trim();


                    if (temp1.equals(userName)) {
                        exist = true;
                        if (temp2.equals(securityQ)) {
                            wrongSecurity.setText("now you can change your password ");
                            GotoChangePassword(event , userName);


                        } else {
                            wrongSecurity.setText("wrong security question answer ");
                        }
                        break;
                    }

                }

                if (!exist) {
                    wrongSecurity.setText("there is no user with this username");
                }
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void GotoChangePassword(ActionEvent event , String user) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("changePassword.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("change password of " + user);
        ChangePassword.userName = user;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
