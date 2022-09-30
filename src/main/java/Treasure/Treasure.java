package treasure;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;

abstract public class Treasure {
    
    protected Room Location;
    protected int OwnerFightBonus = 0;
    protected int AdversaryFightBonus = 0;
    protected int HPBoost = 0;
    protected int TakeDamage = 0;
    protected String TreasureType;
    protected boolean Found = false;


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

        Room room = starting_rooms.get(random_index);

        // Hide treasure there
        this.Location = room;
    }


    /**
     * @return Room
     * 
     * This method returns a Treasure's location.
     */
    public Room getLocation() {
        return this.Location;
    }

    
    /**
     * @return
     */
    public int getFB(){
        return this.OwnerFightBonus;
    }


    /**
     * @return
     */
    public int getAFB(){
        return this.AdversaryFightBonus;
    }


    /**
     * @return
     */
    public int getHPBoost(){
        return this.HPBoost;
    }


    /**
     * @return
     */
    public int getTakeDamage(){
        return this.TakeDamage;
    }


    /**
     * @return
     */
    public String getType(){
        return this.TreasureType;
    }
}