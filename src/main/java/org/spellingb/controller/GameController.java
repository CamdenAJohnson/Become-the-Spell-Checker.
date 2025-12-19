package org.spellingb.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.spellingb.database.UserDAO;
import org.spellingb.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;

import java.awt.*;

public class GameController {

    @FXML
    private TextField userInputField;

    @FXML
    private Button playAudio;

    @FXML
    private Label timer;

    @FXML
    private void handleInput(ActionEvent event) {
        System.out.println(event.toString());
        System.out.println("The user has pressed enter.");

    }

    @FXML
    private void audioButtonPress(ActionEvent event) {
        System.out.println(event.toString());
    }
}
