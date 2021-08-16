package minigames.backend;

import java.util.Random;

public class Room {

    private String[][] room = new String[10][10];
    private String[][] exploredRoom = new String[10][10];
    private Random random = new Random();

    /**
     * Constructor for room tp create a room as well as an empty map of the room
     * before it has been explored
     */
    public Room(){
        //Fill up every cell with an X
        for(int i=0; i<room.length; i++){
            for(int j=0; j<room.length; j++){
                room[i][j] = "X";
                exploredRoom[i][j] = "X";
            }
        }
        createRoom();
        createBombs();
    }


    /**
     * Adds the treasure and bombs to the room
     */
    private void createRoom() {
        int fin = 0;
        while (fin < 3) {
            int treasureX = random.nextInt(room.length);
            int treasureY = random.nextInt(room.length);
            if (room[treasureX][treasureY].equals("X")) {
                room[treasureX][treasureY] = "T";
                fin++;
            }
        }
    }


    /**
     * Adds the bombs to the room
     */
    private void createBombs() {
        int fin = 0;
        while (fin < 8) {
            int bombX = random.nextInt(room.length);
            int bombY = random.nextInt(room.length);

            if (room[bombX][bombY].equals("X")) {
                room[bombX][bombY] = "B";
                fin++;
            }
        }
    }


    /**
     * Returns the room
     * @return - the room
     */
    public String[][] getRoom() {
        return room;
    }

    /**
     * Create a room with a specific layout
     * @param room - The room with a certain layout
     */
    public void setRoom(String[][] room) {
        this.room = room;
    }

    /**
     * Gets the explored room
     * @return - the room explored so far
     */
    public String[][] getExploredRoom() {
        return exploredRoom;
    }

    /**
     * Sets an explored room
     * @param exploredRoom -
     */
    public void setExploredRoom(String[][] exploredRoom) {
        this.exploredRoom = exploredRoom;
    }
}
