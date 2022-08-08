package com.project.twittersimulation.model;


import java.util.ArrayList;

public class User {
    private int number;
    private String username;
    private String accountType;
    private String businessType;
    private String birthDay;

    public static ArrayList<User> allRecommendedUSer = new ArrayList<>();

    //constructor


    public User(int number, String username, String accountType, String businessType, String birthDay) {
        this.number = number;
        this.username = username;
        this.accountType = accountType;
        this.businessType = businessType;
        this.birthDay = birthDay;
    }

    //getters and setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
