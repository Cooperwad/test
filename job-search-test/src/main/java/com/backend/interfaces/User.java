package com.backend.interfaces;

public interface User {

    boolean login();
    boolean logout();
    void createProfile();
    void getProfile();
    void addFavorite();
    void removeFavorite();
}
