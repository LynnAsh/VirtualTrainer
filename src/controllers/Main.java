package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/resources/main.fxml"));
        Scene mainScene = new Scene(mainMenu, 600, 400);

        primaryStage.setTitle("Virtual Trainer");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
