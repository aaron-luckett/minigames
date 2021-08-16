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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Hangman implements Initializable {

    public Circle head;
    public Line body;
    public Line leftArm;
    public Line rightArm;
    public Line leftLeg;
    public Line rightLeg;
    public Line base;
    public Line pole;
    public Line rope;
    public Line roof;

    public TextField letterGuessed;
    public Button enter;

    public Label generatedWord;
    public Label guessedLettersLabel;

    private ArrayList<String> guessedLetters = new ArrayList<>();
    private String[] wordBank = new String[]{"head", "shoulders", "knees", "toes", "ears", "mouth", "nose", "eyeball"};
    private String selectedWord;
    private String coveredWord;
    private int incorrectGuesses = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPartsInvisible();
        selectWord();

        setGuessedWord();
    }

    /**
     * Set the guessed letters label to display guessed letters
     */
    private void setGuessedWord(){
        //If no letters guess, blank label
        if(guessedLetters.size() == 0){
            guessedLettersLabel.setText("");
            //Else will display the guessed letters
        } else {
            for (int i = 0; i < guessedLetters.size(); i++) {
                String guessedLetter = guessedLetters.get(i);
                guessedLettersLabel.setText(guessedLetter + ", ");
            }
        }
    }

    public void reset(){
        setPartsInvisible();
        selectWord();
        guessedLetters.clear();
        setGuessedWord();
    }

    /**
     * Select a word from the word bank
     */
    private void selectWord(){
        //Chooses a word from the bank
        selectedWord = wordBank[(int)(Math.random() * (wordBank.length))];

        //Covers the word
        coveredWord = selectedWord.replaceAll(".", "?");

        //Sets the label
        generatedWord.setText(coveredWord);
    }

    /**
     * Set the parts to be invisible at first
     */
    private void setPartsInvisible(){
        head.setVisible(false);
        body.setVisible(false);
        leftArm.setVisible(false);
        rightArm.setVisible(false);
        leftLeg.setVisible(false);
        rightLeg.setVisible(false);
        rope.setVisible(false);
        roof.setVisible(false);
        pole.setVisible(false);
        base.setVisible(false);
    }

    public void enterLetter(){
        String letterInputted = letterGuessed.getText();

        if(selectedWord.contains(letterInputted)){
            uncoverWord(letterInputted);
            System.out.println("NICE");
            checkForCompletedWord();
        } else {
            incorrectGuess(letterInputted);
        }

        letterGuessed.clear();
    }

    private void uncoverWord(String inputtedWord){
        char character = inputtedWord.charAt(0);

        String partUncover = selectedWord;
        char[] partUncoverArray = partUncover.toCharArray();

        for(int i=0; i<partUncoverArray.length; i++){
            if(partUncoverArray[i] == character){
                char[] temp = coveredWord.toCharArray();
                temp[i] = character;
                coveredWord = String.valueOf(temp);
                generatedWord.setText(coveredWord);
            }
        }
    }

    private void checkForCompletedWord(){
        if(coveredWord.equals(selectedWord)){
            System.out.println("Well done!");
            incorrectGuesses = 0;
            reset();
        }
    }

    private void incorrectGuess(String letterInputted){
        System.out.println("UNLUCKY");
        incorrectGuesses++;
        drawMan(letterInputted);
    }

    private void drawMan(String letterInputted){
        if(incorrectGuesses == 10){
            rightLeg.setVisible(true);
            System.out.println("YOU LOSE");
            incorrectGuesses = 0;
            reset();
        } else {
            if (incorrectGuesses == 1) {
                base.setVisible(true);
            } else if (incorrectGuesses == 2) {
                pole.setVisible(true);
            } else if (incorrectGuesses == 3) {
                roof.setVisible(true);
            } else if (incorrectGuesses == 4) {
                rope.setVisible(true);
            } else if (incorrectGuesses == 5) {
                head.setVisible(true);
            } else if (incorrectGuesses == 6) {
                body.setVisible(true);
            } else if (incorrectGuesses == 7) {
                leftArm.setVisible(true);
            } else if (incorrectGuesses == 8) {
                rightArm.setVisible(true);
            } else if (incorrectGuesses == 9) {
                leftLeg.setVisible(true);
            }
            addToIncorrectGuess(letterInputted);
        }
    }

    private void addToIncorrectGuess(String letterInputted){
        if(!guessedLetters.contains(letterInputted)) {
            guessedLetters.add(letterInputted);
        }
        //System.out.println(guessedLetters);
        guessedLettersLabel.setText(String.valueOf(guessedLetters));
    }

    /**
     * Changes the scene to the home scene
     * @param event - Button click
     * @throws IOException - No button was clicked
     */
    public void changePage(ActionEvent event) throws IOException{
        Parent TestParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene TestScene = new Scene(TestParent);
        Stage Page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Page.setScene(TestScene);
        Page.show();
    }
}
