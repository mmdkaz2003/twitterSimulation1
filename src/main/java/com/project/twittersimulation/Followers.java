package com.project.twittersimulation;


import java.util.ArrayList;

public class Followers {

    private String followerName;

    public static ArrayList<String> followersList =  new ArrayList<>();

    @Override
    public String toString() {
        return this.followerName;
    }

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    public Followers(String name) {
        this.followerName = name;
    }
}
