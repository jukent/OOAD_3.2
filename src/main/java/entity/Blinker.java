package entity;

import java.util.ArrayList;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;
import movement.BlinkMovement;

public class Blinker extends Creature { 
    // Example of inheritance
    
    
    /**
     * @param id: int
     * @param map: Dungeon
     * 
     * Blinkers constructor must be passed an ID integer 'id' and the Dungeon
     * Blinkers are assigned a starting room at construction.
     */
    public Blinker(int id, Dungeon map) {
        super.id = id; // Blinker ID value
        this.dungeon = map; // Game Dungeon
        this.movementBehavior = new BlinkMovement(); // MovementType is Blink
        name = "Blinker";

        setStartingRoom(); //Blinkers start anywhere on the 4th level
    }


    /* (non-Javadoc)
     * @see entity.creature.Creature#setStartingRoom()
     * 
     * Randomly generate starting room for Blinker from any room on 4th level.
     */
    @Override
    protected void setStartingRoom() {
        // Generate an ArrayList of possible starting rooms
        // Generating a fresh list in a for-looped seemed simpler than removing every room not on the 4th level 
        // as done in the `move()` method
        // ArrayList values point to identical Room objects as in the main dungeon map
        ArrayList<Room> startingRooms = new ArrayList<Room>();

        for (int r = 0; r < 2; ++r) { // row
            for (int c = 0; c < 2; ++c) { // column
                String roomName = new String("(4-" + r + "-" + c + ")"); 
                startingRooms.add(dungeon.getRoom(roomName));   
            }
        }
                
        // Randomly select one of the rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        Random random = new Random();
        int i = random.nextInt(startingRooms.size());

        Room newRoom = startingRooms.get(i);

        // Start there
        this.setLocation(newRoom);
    }
}
