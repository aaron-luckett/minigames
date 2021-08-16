package minigames.frontend;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import minigames.backend.Room;

import java.net.URL;
import java.util.ResourceBundle;

public class BombController implements Initializable {

    public GridPane mapOfRoom;

    public TextField rowEntry;
    public TextField colEntry;

    public Label correctInput;
    public Label resultOfRoom;

    private String[][] room;
    private String[][] exploredRoom;

    public Button entry;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Room r = new Room();
        room = r.getRoom();
        exploredRoom = r.getExploredRoom();
        fillRoom();

        correctInput.setVisible(false);
        resultOfRoom.setVisible(false);
    }

    private void fillRoom(){
        for(int i=0; i<mapOfRoom.getRowCount(); i++){
            for(int j=0; j<mapOfRoom.getColumnCount(); j++){
                Rectangle rec = new Rectangle(18,18);

                if(exploredRoom[i][j].equals("B")) {
                    rec.setFill(Color.RED);
                } else if(exploredRoom[i][j].equals("T")){
                    rec.setFill(Color.YELLOW);
                } else if(exploredRoom[i][j].equals("E")) {
                    rec.setFill(Color.WHITE);
                } else {
                    rec.setFill(Color.BLACK);
                }
                mapOfRoom.add(rec, i, j);
            }
        }
    }

    private int getRow(){
        return Integer.parseInt(rowEntry.getText());
    }

    private int getColumn(){
        return Integer.parseInt(colEntry.getText());
    }

    public void enterCell(){
        int rowNumber = getRow();
        int colNumber = getColumn();

        //While not in range keep asking user for input
        if ((colNumber < 0 || colNumber > (room.length - 1)) || (rowNumber < 0 || rowNumber > (room.length - 1))) {
            clearInputs(true);
        } else {
            clearInputs(false);
            processEntry(rowNumber, colNumber);
        }

    }

    private void processEntry(int rowNumber, int colNumber){
        //If T, user wins
        if (room[rowNumber][colNumber].equals("T")) {
            System.out.println("YOU WIN");
            exploredRoom[rowNumber][colNumber] = "T";

            resultOfRoom.setText("YOU WIN");
            resultOfRoom.setVisible(true);
            endGame();

            //If B, user looses
        } else if (room[rowNumber][colNumber].equals("B")) {
            System.out.println("Explosion! You LOSE!");
            exploredRoom[rowNumber][colNumber] = "B";

            resultOfRoom.setText("YOU LOSE");
            resultOfRoom.setVisible(true);
            endGame();

        } else {
            //Carry on, found nothing
            exploredRoom[rowNumber][colNumber] = "E";
        }

        fillRoom();
    }

    private void clearInputs(boolean show){
        correctInput.setVisible(show);
        rowEntry.clear();
        colEntry.clear();
    }

    private void endGame(){
        rowEntry.setVisible(false);
        colEntry.setVisible(false);
        entry.setVisible(false);
        fillRoom();

    }
}
