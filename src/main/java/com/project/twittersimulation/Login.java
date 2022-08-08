package com.project.twittersimulation;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import com.project.twittersimulation.model.AccountType;
import com.project.twittersimulation.model.BusinessAccount;
import com.project.twittersimulation.model.NormalAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class Login  {



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


                            MenuController.password = passWord;
                            MenuController.name=resultSet.getString("name");
                            MenuController.userName=userName;
                            MenuController.theme = resultSet.getString("theme");
                            MenuController.security=resultSet.getString("securityQ");




                            if(resultSet.getString("accounttype").equals(AccountType.BusinessAccount.toString())){
                                BusinessAccount businessAccount=new BusinessAccount(resultSet.getString("name"),resultSet.getString("username"),
                                        resultSet.getString("password"),resultSet.getString("password"),
                                        resultSet.getString("businessType"),resultSet.getInt("id"));
                                MenuController.businessAccount=businessAccount;
                                MenuController.normalAccount=null;

                            }
                            else{
                                MenuController.birthDay = resultSet.getString("birthday").trim();
                                NormalAccount normalAccount=new NormalAccount(resultSet.getString("name"),resultSet.getString("username"),
                                        resultSet.getString("password"),resultSet.getString("password"),
                                        LocalDate.parse(resultSet.getString("birthday")),resultSet.getInt("id"));
                                MenuController.normalAccount=normalAccount;
                                MenuController.businessAccount=null;
                            }
                            GoToMenu(event);

                        }
                        else {
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
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        App.scene.setRoot(pane);
    }

    public void GoToMenu(ActionEvent event) throws IOException{
        String css1;
        String css2;

        if (MenuController.theme.equals("classic mode")){
            App.scene.getStylesheets().clear();
            css1 = this.getClass().getResource("/CSS/classicMode.css").toExternalForm();
            css2 = this.getClass().getResource("/CSS/buttonColorClassic.css").toExternalForm();

            App.scene.getStylesheets().add(css1);
            App.scene.getStylesheets().add(css2);

        }
        else {
            App.scene.getStylesheets().clear();
            css1 = this.getClass().getResource("/CSS/darkMode.css").toExternalForm();
            css2 = this.getClass().getResource("/CSS/buttonColorDark.css").toExternalForm();

            App.scene.getStylesheets().add(css1);
            App.scene.getStylesheets().add(css2);

        }

        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("Menu.fxml"));
        App.scene.setRoot(pane);
    }



    public void Create(ActionEvent event) throws IOException {
        Pane pane=null;
        pane= FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
        App.scene.setRoot(pane);
    }


}












