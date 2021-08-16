package minigames.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import minigames.backend.GuessingNumber;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NumberGuesser implements Initializable {

    //Instance Variables
    public Button homeButton; //Button to go home
    public Button reset;      //Button to reset scene
    public Button enter;      //Button to enter guess

    public TextField numberEntry;   //Entry field to enter guess

    public Label higherLower;    //Whether the previous guess was too high or low
    public Label guessesLabel;   //The number of guesses
    public Label actualNumber;   //The actual number
    public Label quickGuess;     //Shows the quickest guess

    private int numOfGuesses = 0;    //The number of guesses for that round
    private int actualNumberValue;   //The actual number generated
    private int quickestGuess = -1;  //The quickest guess so far

    private GuessingNumber numberGenerated;  //The number generated

    /**
     * Initializes the scene
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //The label values and makes them visible/invisible
        higherLower.setVisible(false);
        guessesLabel.setText(String.valueOf(numOfGuesses));
        quickGuess.setVisible(false);

        //Generates a number to be guessed
        numberGenerated = new GuessingNumber();
        actualNumberValue = numberGenerated.getComputerNumber();
    }


    /**
     * Changes pane to the home one
     * @param event - Button click
     * @throws IOException - No button click
     */
    public void changePage(ActionEvent event) throws IOException {
        Parent TestParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene TestScene = new Scene(TestParent);
        Stage Page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Page.setScene(TestScene);
        Page.show();
    }


    /**
     * Resets the scene
     */
    public void resetNumber(){
        //Generates new number and number of guesses
        actualNumberValue = numberGenerated.generateNewNumber();
        numOfGuesses = 0;
        guessesLabel.setText(String.valueOf(numOfGuesses));
        higherLower.setVisible(false);
    }


    /**
     * Gets the answer from the user to check it
     */
    public void getAnswer(){
        int answer = -1;

        //If something entered convert it to string
        if(!numberEntry.getText().equals("")){
            answer = Integer.parseInt(numberEntry.getText());
        } else {
            //Else tell user nothing has been entered
            higherLower.setVisible(true);
            higherLower.setText("No value entered");
        }

        //If answer entered compare to the number generated
        if(answer != - 1) {
            //If lower, tell the user guess was too low and update guess counter
            if (answer < actualNumberValue) {
                higherLower.setVisible(true);
                higherLower.setText("You were too low");
                updateCounter();
            //If too high, do the same
            } else if (answer > actualNumberValue) {
                higherLower.setVisible(true);
                higherLower.setText("You were too high");
                updateCounter();
            } else {
                //If correct, tell the user and update the counters
                higherLower.setVisible(true);
                higherLower.setText("You were correct!");
                updateCounter();
                correctAnswer();
                //Load a fresh number
                resetNumber();

            }
        }
        numberEntry.clear();
    }


    /**
     * Updates the number of guess counter
     */
    private void updateCounter(){
        numOfGuesses ++;
        guessesLabel.setText(String.valueOf(numOfGuesses));
    }


    /**
     * Will update counters and values upon a correct answer
     */
    private void correctAnswer(){
        if(quickestGuess == -1){
            quickestGuess = numOfGuesses;
            quickGuess.setText(String.valueOf(quickestGuess));
            quickGuess.setVisible(true);
        } else {
            if(numOfGuesses < quickestGuess){
                quickestGuess = numOfGuesses;
                quickGuess.setText(String.valueOf(quickestGuess));
            }
        }
    }
}
