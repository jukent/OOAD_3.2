import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class BlinkMovement extends MovementBehavior {


    public BlinkMovement() {
        this.MovementType = "Blink";
    }

    
    /* (non-Javadoc)
     * @see MovementBehavior#move()
     * 
     * Replace abstract movement with Blinker movement
     */
    @Override
    public void move(Entities entity, Dungeon dungeon) {
        Room current_room = entity.getLocation();

        // Get a new hashtable to store possible rooms called 'possible_room_map'
        // Hashtable values point to a subset of the Room objects in the main dungeon map
        Hashtable<String, Room> possible_room_map = new Hashtable<String, Room>();
        possible_room_map.putAll(dungeon.getMap()); // Learned method from Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?(https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/)
        possible_room_map.remove("0-1-1"); // Remove entrace room
        possible_room_map.remove(current_room.getName()); // Remove current room

        // Randomly select one of the rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        // Convert values to an ArrayList of Room so that Rooms can grabbed via index
        ArrayList<Room> possible_rooms = new ArrayList<Room>(possible_room_map.values());

        // Get random index from 0 (inclusive) to the length of possible rooms (exclusive)
        Random random = new Random();
        int random_index = random.nextInt(possible_rooms.size());

        // Grab a Room from the random index
        Room new_room = possible_rooms.get(random_index);
            
        // Move there
        entity.setLocation(new_room);  
    } 
}
