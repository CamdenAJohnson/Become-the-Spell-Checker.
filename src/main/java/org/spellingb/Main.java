package org.spellingb;

import org.spellingb.database.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseHelper.getConnection(); // Connect to database at startup

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DatabaseHelper.closeConnection(); // Close DB connection on app shutdown
    }

    public static void main(String[] args) {
        launch(args);
    }
}