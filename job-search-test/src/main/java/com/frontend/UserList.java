package com.frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {

    // ArrayList to store User objects
    private ArrayList<User> userList;

    // Constructor initializes the ArrayList and loads existing user data from a file
    public UserList() {
        userList = new ArrayList<>();
        loadUsers();
    }

    // Getter to retrieve the user list
    public ArrayList<User> getUserList() {
        return userList;
    }

    // Load user data from a file into the ArrayList
    private void loadUsers() {
        try {
            Scanner input = new Scanner(new File("userinfo.txt"));
            while (input.hasNextLine()) {
                // Read each line of user data from the file
                String userInfo = input.nextLine();
                // Split the user data into individual components using comma as a delimiter
                String[] userInfoDataSplit = userInfo.split(",");
                // Check if the data has the expected format (username, password, email)
                if (userInfoDataSplit.length >= 3) {
                    // Extract components and create a new User object
                    String username = userInfoDataSplit[0];
                    String password = userInfoDataSplit[1];
                    String email = userInfoDataSplit[2];
                    User user = new User(username, password, email);
                    // Add the new User object to the ArrayList
                    userList.add(user);
                } else {
                    // Display an error message for invalid user data
                    System.out.println("Invalid user data: " + userInfo);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    // Save a new user to the ArrayList and update the user data file
    public void saveUser(User newUser) {
        // Add the new user to the ArrayList
        userList.add(newUser);

        try {
            // Open a FileWriter in append mode to add the new user data to the file
            FileWriter writer = new FileWriter("userinfo.txt", true);
            // Write the new user data to the file
            writer.write(newUser.getUsername() + "," + newUser.getPassword() + "," + newUser.getEmail() + "\n");
            // Close the FileWriter
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving user to file: " + e.getMessage());
        }
    }
}