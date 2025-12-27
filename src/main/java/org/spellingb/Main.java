package org.spellingb;

import org.spellingb.database.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.spellingb.util.StageManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseHelper.getConnection(); // Connect to database at startup
        StageManager.initStageManager(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        StageManager.showScene(loader, "Login");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DatabaseHelper.closeConnection(); // Close DB connection on application shutdown
    }

    public static void main(String[] args) {
        launch(args);
    }
}