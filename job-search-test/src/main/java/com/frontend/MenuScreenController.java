package com.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuScreenController {

    @FXML
    private Button logoutBtn;

    // Takes user back to login screen when "Logout" button is clicked
    @FXML
    void logout(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage thisStage = (Stage) logoutBtn.getScene().getWindow();
            thisStage.setTitle("Login Screen");
            thisStage.setScene(scene);
            thisStage.show();
        } catch (IOException e) {
            System.out.println("Couldn't load that fxml file!");
        }

    }

    // Initializes the controller when the FXML file is loaded (not currently used)
    public void initialize(){

    }

}