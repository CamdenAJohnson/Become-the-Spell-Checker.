package org.spellingb.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.spellingb.database.UserDAO;
import org.spellingb.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class HomeController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            User loggedInUser = UserDAO.login(username, password);

            if (loggedInUser != null) {
                // Successful login → Go to Dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
                Scene scene = new Scene(loader.load());

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Dashboard");
                stage.show();
            } else {
                messageLabel.setText("Invalid username or password.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }

        } catch (Exception e) {
            messageLabel.setText("Database error.");
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            Scene scene = new Scene(loader.load());

            // Get the current window (Stage) and set the new scene
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
