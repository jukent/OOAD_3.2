import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

abstract public class Treasure {
    
    protected Room Location;
    protected int OwnerFightBonus = 0;
    protected int AdversaryFightBonus = 0;
    protected int HPBoost = 0;
    protected String TreasureType;


    /**
     * @param dungeon
     */
    protected void setLocation(Dungeon dungeon) {
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
        this.Location = new_room;
    }


    /**
     * @return Room
     * 
     * This method returns a Treasure's location.
     */
    public Room getLocation() {
        return this.Location;
    }
}
