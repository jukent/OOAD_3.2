import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Orbiter extends Creature {
    // Example of inheritance

    String direction = "clockwise"; // Need to add a default

    Orbiter(int A, Dungeon map) {
        super.ID = A;
        this.dungeon = map;
        name = "Orbiter";
        this.MovementBehavior = new OrbitMovement();
        setStartingRoom(); // Set starting room
        this.direction = setDirection(); // Clockwise or Counterclockwise
    }


    /**
     * Randomly generate starting room for orbiters from any exterior room on any level
     */
    protected void setStartingRoom() {
        // Get map of possible rooms pointing to identical Room objects as in main dungeon.
        Hashtable<String, Room> possible_room_map = new Hashtable<String, Room>();
        possible_room_map.putAll(dungeon.getMap()); // Learned method from Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?(https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/)
        possible_room_map.remove("(0-1-1)"); // remove entrace room
        possible_room_map.remove("(1-1-1)"); // remove 1st floor center room
        possible_room_map.remove("(2-1-1)"); // remove 2nd floor center room
        possible_room_map.remove("(3-1-1)"); // remove 3rd floor center room
        possible_room_map.remove("(4-1-1)"); // remove 4th floor center room
                
        // Randomly select one of the rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        ArrayList<Room> starting_rooms = new ArrayList<Room>(possible_room_map.values());
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);

        // Start there
        this.setLocation(new_room);
    }

    /**
     * @return String
     * 
     * Randomly return "clockwise" or "counterclockwise" to set Orbiter direction
     */
    private String setDirection() {
        ArrayList<String> directions = new ArrayList<String>();
        directions.add("clockwise");
        directions.add("counterclockwise");

        Random random = new Random();
        int random_index = random.nextInt(2);

        String direction = directions.get(random_index);
        return direction;
    }
}
