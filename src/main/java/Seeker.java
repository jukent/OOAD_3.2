package main.java;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Seeker extends Creature {
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Construct a Seeker with ID `A` and the Dungeon.
     */
    public Seeker(int A, Dungeon map) {
        this.dungeon = map;
        super.ID = A;
        setStartingRoom();
        name = "Seeker";
        this.MovementBehavior = new SeekMovement();
    }


    /**
     * Randomly generate starting room for orbiters from any exterior room on any level
     */
    protected void setStartingRoom() {
        // Get new map of possible Rooms
        Hashtable<String, Room> possible_room_map = new Hashtable<String, Room>();
        possible_room_map.putAll(dungeon.getMap()); // Learned method from Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?(https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/)
        possible_room_map.remove("(0-1-1)"); // Remove entrace room
                
        // Randomly select one of the Rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        ArrayList<Room> starting_rooms = new ArrayList<Room>(possible_room_map.values());
        
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);

        // Start there
        this.setLocation(new_room);
    }
}
