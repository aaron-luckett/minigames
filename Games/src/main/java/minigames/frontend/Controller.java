/*
 * Controller.java  1.2.2  1/05/2020
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 */

package minigames.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Aaron Luckett (aal16)
 */
public class Controller implements Initializable {

    /**
     * Loads up the scene
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Changes the scene to the number guesser scene
     * @param event - Button click
     * @throws IOException - No button was clicked
     */
    public void changePage(ActionEvent event) throws IOException{
        Parent TestParent = FXMLLoader.load(getClass().getResource("NumberGuesser.fxml"));
        Scene TestScene = new Scene(TestParent);
        Stage Page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Page.setScene(TestScene);
        Page.show();
    }

    public void changePageHang(ActionEvent event) throws IOException{
        Parent TestParent = FXMLLoader.load(getClass().getResource("Hangman.fxml"));
        Scene TestScene = new Scene(TestParent);
        Stage Page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Page.setScene(TestScene);
        Page.show();
    }
}
