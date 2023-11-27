package com.example.csc214groupproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginScreenController {

    @FXML
    private Button loginBtn;
    @FXML
    private Button accountCreationBtn;
    @FXML
    private Text errorTxt;
    @FXML
    private TextField passwordTxtField;
    @FXML
    private TextField usernameTxtField;

    // Reference to UserList to manage user data
    private UserList userList;

    // Switch to the account creation screen when the "Create Account" button is pressed
    @FXML
    void createAccount(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AccountCreationScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage thisStage = (Stage) accountCreationBtn.getScene().getWindow();
            thisStage.setTitle("Account Creation");
            thisStage.setScene(scene);
            thisStage.show();
        } catch (IOException e) {
            System.out.println("Couldn't load that fxml file!");
        }
    }

    // Validate user credentials and switch to the menu screen if valid
    @FXML
    void login(ActionEvent event) {
        boolean isCredentialsValid = false;

        // Check entered credentials against existing user data
        for (User user : userList.getUserList()) {
            if (usernameTxtField.getText().equals(user.getUsername()) && passwordTxtField.getText().equals(user.getPassword())) {
                isCredentialsValid = true;

                // Switch to the menu screen if credentials are valid
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuScreen.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage thisStage = (Stage) loginBtn.getScene().getWindow();
                    thisStage.setTitle("Menu");
                    thisStage.setScene(scene);
                    thisStage.show();
                } catch (IOException e) {
                    System.out.println("Couldn't load that fxml file!");
                }

                break;
            }
        }

        // Show an error message if credentials are invalid
        if (!isCredentialsValid) {
            errorTxt.setOpacity(1);
        } else {
            errorTxt.setOpacity(0);
        }
    }

    // Initializes the controller when the FXML file is loaded, creating a new UserList instance to
    // manage user data during account creation.
    public void initialize() {
        userList = new UserList();
    }
}
