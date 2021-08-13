package minigames.backend;

public class GuessingNumber {

    private int computerNumber; //Number to be generated

    /**
     * Constructor for the number to be guessed
     */
    public GuessingNumber(){
        computerNumber = (int) (Math.random() * 100 + 1);
        //System.out.println(computerNumber);
    }

    /**
     * Returns the generated number
     * @return - The number generated
     */
    public int getComputerNumber() {
        return computerNumber;
    }


    /**
     * Sets the number
     * @param computerNumber - The number the value should be
     */
    public void setComputerNumber(int computerNumber) {
        this.computerNumber = computerNumber;
    }


    /**
     * Generates a number number for a new round of guessing
     * @return - The new number generated
     */
    public int generateNewNumber(){
        //System.out.println("New Number ");
        return (int) (Math.random() * 100 + 1);
    }
}
