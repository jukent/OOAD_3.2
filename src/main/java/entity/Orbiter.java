package entity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;
import movement.OrbitMovement;

public class Orbiter extends Creature {
    // Example of inheritance

    String direction = "clockwise"; // Need to add a default


    /**
     * @param id: int
     * @param map: Dungeon
     * 
     * Constructor for Orbiters must be passed an ID integer 'id' and the Dungeon.
     * Orbiters are assigned a starting room at construction.
     */
    public Orbiter(int id, Dungeon map) {
        super.id = id; // Orbiter ID value
        this.dungeon = map; // Game Dungeon
        this.movementBehavior = new OrbitMovement(); // MovementType is Orbit
        name = "Orbiter"; // String name
        
        setStartingRoom(); // Orbiters start in any outside Room
        setRandomDirection(); // Clockwise or Counterclockwise
    }


    /* (non-Javadoc)
     * @see entity.creature.Creature#setStartingRoom()
     * 
     * Randomly generate starting room for Orbiters from any exterior room on any level
     */
    protected void setStartingRoom() {
        // Get map of possible rooms pointing to identical Room objects as in main dungeon.
        Hashtable<String, Room> possibleRoomMap = new Hashtable<String, Room>();
        possibleRoomMap.putAll(dungeon.getMap()); // Learned method from Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?(https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/)
        possibleRoomMap.remove("(0-1-1)"); // remove entrace room
        possibleRoomMap.remove("(1-1-1)"); // remove 1st floor center room
        possibleRoomMap.remove("(2-1-1)"); // remove 2nd floor center room
        possibleRoomMap.remove("(3-1-1)"); // remove 3rd floor center room
        possibleRoomMap.remove("(4-1-1)"); // remove 4th floor center room
                
        // Randomly select one of the rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        ArrayList<Room> startingRooms = new ArrayList<Room>(possibleRoomMap.values());
        Random random = new Random();
        int i = random.nextInt(startingRooms.size());

        Room newRoom = startingRooms.get(i);

        // Start there
        this.setLocation(newRoom);
    }

    /**
     * @return String
     * 
     * Randomly return "clockwise" or "counterclockwise" to set Orbiter direction
     */
    private void setRandomDirection() {
        ArrayList<String> directions = new ArrayList<String>();
        directions.add("clockwise");
        directions.add("counterclockwise");

        Random random = new Random();
        int i= random.nextInt(2);

        String direction = directions.get(i);
        setDirection(direction);
    }
}
