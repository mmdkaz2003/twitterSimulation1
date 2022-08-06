package com.project.twittersimulation;

import java.util.ArrayList;

public class Followings {

    private String followingName;

    @Override
    public String toString() {
        return this.followingName;
    }

    public String getFollowingName() {
        return followingName;
    }

    public void setFollowingName(String followingName) {
        this.followingName = followingName;
    }

    public Followings(String name) {
        this.followingName = name;
    }


    public static ArrayList<String> followingsList =  new ArrayList<>();
}
