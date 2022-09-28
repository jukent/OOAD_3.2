import java.util.ArrayList;
import java.util.Random;

public class Blinker extends Creature { 
    // Example of inheritance
    
    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Blinkers constructor must be passes an ID integer 'A' and the Dungeon
     * Blinkers are assigned a starting room at construction.
     */
    Blinker(int A, Dungeon map) {
        super.ID = A;
        this.dungeon = map;
        this.MovementBehavior = new BlinkMovement();
        name = "Blinker";

        //Blinkers start anywhere on the 4th level
        setStartingRoom();
    }


    /* (non-Javadoc)
     * @see Creatures#setStartingRoom()
     * 
     * Randomly generate starting room for blinker from any room on 4th level
     */
    @Override
    protected void setStartingRoom() {
        // Generate an ArrayList of possible starting rooms
        // Generating a fresh list in a for-looped seemed simpler than removing every room not on the 4th level 
        // as done in the `move()` method
        // ArrayList values point to identical Room objects as in the main dungeon map
        ArrayList<Room> starting_rooms = new ArrayList<Room>();

        for (int r = 0; r < 2; ++r) { // row
            for (int c = 0; c < 2; ++c) { // column
                String room_name = new String("(4-" + r + "-" + c + ")"); 
                starting_rooms.add(dungeon.getRoom(room_name));   
            }
        }
                
        // Randomly select one of the rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);

        // Start there
        this.setLocation(new_room);
    }
}
