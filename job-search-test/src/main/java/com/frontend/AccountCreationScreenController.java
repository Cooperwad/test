package com.frontend;

import com.backend.Profile;
import com.backend.ResumeParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class AccountCreationScreenController {

    @FXML
    private Button backBtn;
    @FXML
    private TextField usernameTxtField;
    @FXML
    private Text confPasswordErrorTxt;
    @FXML
    private TextField confPasswordTxtField;
    @FXML
    private Button createAccountBtn;
    @FXML
    private Text emailErrorTxt;
    @FXML
    private TextField emailTxtField;
    @FXML
    private Text passwordErrorTxt;
    @FXML
    private TextField passwordTxtField;
    @FXML
    private Text usernameErrorTxt;

    @FXML
    private Button resumeUploadButton;

    // Reference to UserList to manage user data
    private UserList userList;

    // Switch to the login screen when the "Back" button is pressed
    @FXML
    void backToLogin(ActionEvent event) {
        try {
            URL url = new File("job-search-test\\src\\main\\java\\com\\frontend\\resources\\LoginScreen.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root, 600, 400);
            Stage thisStage = (Stage) backBtn.getScene().getWindow();
            thisStage.setTitle("Login Screen");
            thisStage.setScene(scene);
            thisStage.show();
        } catch (IOException e) {
            System.out.println("Couldn't load that fxml file!");
        }
    }

    // Validate user input and create an account if input is valid
    @FXML
    void createAccount(ActionEvent event) {
        boolean isEmailValid = true;
        boolean isUsernameValid = true;
        boolean isPasswordValid;
        boolean isConfPasswordValid;

        // Validate email input
        if (emailTxtField.getText().isEmpty()) {
            isEmailValid = false;
            emailErrorTxt.setText("*Missing field required");
            emailErrorTxt.setOpacity(1);
        } else {
            // Check if the email is already in use
            for (User user : userList.getUserList()) {
                if (user.getEmail().equals(emailTxtField.getText())) {
                    isEmailValid = false;
                    emailErrorTxt.setText("*Email has already been used");
                    emailErrorTxt.setOpacity(1);
                    break;
                }
            }
        }

        // Hide email error text if input is valid
        if (isEmailValid) {
            emailErrorTxt.setOpacity(0);
        }

        // Validate username input
        if (usernameTxtField.getText().isEmpty()) {
            isUsernameValid = false;
            usernameErrorTxt.setText("*Missing field required");
            usernameErrorTxt.setOpacity(1);
        } else {
            // Check if the username is already taken
            for (User user : userList.getUserList()) {
                if (user.getUsername().equals(usernameTxtField.getText())) {
                    isUsernameValid = false;
                    usernameErrorTxt.setText("*Username has already been taken");
                    usernameErrorTxt.setOpacity(1);
                    break;
                }
            }
        }

        // Hide username error text if input is valid
        if (isUsernameValid) {
            usernameErrorTxt.setOpacity(0);
        }

        // Validate password input
        if (passwordTxtField.getText().length() > 5) {
            isPasswordValid = true;
            passwordErrorTxt.setOpacity(0);
        } else {
            isPasswordValid = false;
            passwordErrorTxt.setOpacity(1);
        }

        // Validate confirmation password input
        if (passwordTxtField.getText().equals(confPasswordTxtField.getText())) {
            isConfPasswordValid = true;
            confPasswordErrorTxt.setOpacity(0);
        } else {
            isConfPasswordValid = false;
            confPasswordErrorTxt.setOpacity(1);
        }

        // If all inputs are valid, create a new user and switch to the login screen
        if (isEmailValid && isUsernameValid && isPasswordValid && isConfPasswordValid) {
            User newUser = new User(usernameTxtField.getText(), passwordTxtField.getText(), emailTxtField.getText());
            userList.saveUser(newUser);

            try {
                URL url = new File("job-search-test\\src\\main\\java\\com\\frontend\\resources\\LoginScreen.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Scene scene = new Scene(root, 600, 400);
                Stage thisStage = (Stage) createAccountBtn.getScene().getWindow();
                thisStage.setTitle("Login Screen");
                thisStage.setScene(scene);
                thisStage.show();
            } catch (IOException e) {
                System.out.println("Couldn't load that fxml file!");
            }
        }
    }
    private void handleFileUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Resume");

        File file = fileChooser.showOpenDialog(resumeUploadButton.getScene().getWindow());

        if (file != null) {
            processSelectedFile(file);
        }
    }

    private void processSelectedFile(File file) {
        try {
            byte[] resumeData = Files.readAllBytes(file.toPath());
            ResumeParser resumeParser = new ResumeParser();
            String resumeText = resumeParser.parseResume(resumeData);

            String extractedEmail = resumeParser.extractEmail(resumeText);
            String extractedName = resumeParser.extractName(resumeText);

            // splits name into first and last name
            String[] nameParts = extractedName != null ? extractedName.split("\\s+", 2) : new String[0];
            String firstName = nameParts.length > 0 ? nameParts[0] : "";
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            // create a profile with the extracted information
            Profile userProfile = new Profile();
            userProfile.setFirstName(firstName);
            userProfile.setLastName(lastName);
            userProfile.setEmail(extractedEmail);
            userProfile.setResume(resumeData);

            usernameTxtField.setText(firstName + lastName);
            emailTxtField.setText(extractedEmail);


        } catch(IOException | TikaException | SAXException e) {
            e.printStackTrace();
        }

    }
    // Initializes the controller when the FXML file is loaded, creating a new UserList instance to
    // manage user data during account creation.
    public void initialize() {
        userList = new UserList();
        resumeUploadButton.setOnAction(event -> handleFileUpload());
    }

}