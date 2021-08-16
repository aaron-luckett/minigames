/*
 * Main.java  1.1.3 1/05/2020
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 */

package minigames.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Aaron Luckett (aal16)
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setTitle("Games");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
