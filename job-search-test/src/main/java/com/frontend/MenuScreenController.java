package com.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MenuScreenController {

    @FXML
    private Button logoutBtn;

    // Takes user back to login screen when "Logout" button is clicked
    @FXML
    void logout(ActionEvent event) {

        try {
            URL url = new File("job-search-test\\src\\main\\java\\com\\frontend\\resources\\LoginScreen.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root, 600, 400);
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