package treasure;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;

abstract public class Treasure {
    
    protected Room location; // The Treasure's hiding room.
    protected int ownerFightBonus = 0; // Integer amount of added strength buff this Treasure gives a Character
    protected int adversaryFightBonus = 0; // Integer amount of added strength buff this Treasure gives a Creature
    protected int hpBoost = 0; // Integer amount of added health points this Treasure gives a Character
    protected int takeDamage = 0; // Integer amount of damage this Treasure does to a Character
    protected String treasureType; // String type of Treasure


    /**
     * @param dungeon: Dungeon
     * 
     * Sets the starting Room where the Treasure is hidden.
     */
    protected void hide(Dungeon dungeon) {
        // Get new map of possible Rooms
        Hashtable<String, Room> possibleRoomMap = new Hashtable<String, Room>();
        possibleRoomMap.putAll(dungeon.getMap()); // Learned method from Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?(https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/)
        possibleRoomMap.remove("(0-1-1)"); // Remove entrace room
                
        // Randomly select one of the Rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        ArrayList<Room> rooms = new ArrayList<Room>(possibleRoomMap.values());
        
        Random random = new Random();
        int i = random.nextInt(rooms.size());

        Room room = rooms.get(i);

        // Hide treasure there
        setLocation(room);
    }


    /**
     * @param room: Rooom
     * 
     * This method sets a Treasure's location.
     */
    public void setLocation(Room room) {
        this.location = room;
    }

    /**
     * @return Room
     * 
     * This method returns a Treasure's location.
     */
    public Room getLocation() {
        return this.location;
    }

    
    /**
     * @return int
     * 
     * This method returns a Treasure's ownerFightBonus.
     */
    public int getFightBonus() {
        return this.ownerFightBonus;
    }


    /**
     * @return int
     * 
     * This method returns a Treasure's adversaryFightBonus.
     */
    public int getAdversaryFightBonus() {
        return this.adversaryFightBonus;
    }


    /**
     * @return int
     * 
     * This method returns the hp Boost for the Treasure.
     */
    public int getHPBoost() {
        return this.hpBoost;
    }


    /**
     * @return int
     * 
     * This method returns how much damage the Treasure inflicts.
     */
    public int getTakeDamage() {
        return this.takeDamage;
    }


    /**
     * @return String
     * 
     * This method returns the String for the Treasure type.
     */
    public String getType() {
        return this.treasureType;
    }
}
