package com.project.twittersimulation.model;


public class User {
    private int number;
    private String username;
    private AccountType accountType;
    private BusinessType businessType;
    private String birthDay;


    //constructor


    public User(int number, String username, AccountType accountType, BusinessType businessType, String birthDay) {
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
