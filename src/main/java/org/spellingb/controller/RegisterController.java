package org.spellingb.controller;

import org.spellingb.util.StageManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.spellingb.database.UserDAO;
import org.spellingb.model.User;
import org.spellingb.util.PasswordHasher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = PasswordHasher.hash(passwordField.getText());
        String confirmPassword = PasswordHasher.hash(confirmPasswordField.getText());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Passwords do not match.");
            return;
        }

        try {
            User newUser = new User(-1 , username, password);
            UserDAO.insertUser(newUser);

            messageLabel.setText("Registration successful!");
            messageLabel.setStyle("-fx-text-fill: green;");

            System.out.println("User registered: " + username);

        } catch (SQLException e) {
            messageLabel.setText("Username already exist.");
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            StageManager.showScene(loader, "Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
