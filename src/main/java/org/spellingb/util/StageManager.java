package org.spellingb.util;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class StageManager {
    static private Stage currentStage;
    static private Scene currentScene;

    public static void initStageManager(Stage primaryStage) throws Exception {
        currentStage = primaryStage;
    }

    public static void showScene(FXMLLoader loader, String title) throws Exception {
        Scene scene = new Scene(loader.load());
        currentScene = scene;
        currentStage.setScene(scene);
        currentStage.setTitle(title);
        currentStage.show();
    }

    public static Stage getPrimaryStage() {
        return currentStage;
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }
}
