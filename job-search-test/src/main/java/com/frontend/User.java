package com.frontend;


import com.backend.Profile;

public class User {

    private String username;

    private String password;

    private String email;

    private Profile userProfile;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(Profile userProfile) {
        this.userProfile = userProfile;
    }


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userProfile = new Profile();
    }




}
