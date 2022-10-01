package treasure;

import dungeon.Dungeon;

public class Sword extends Treasure {
    
    
    /**
     * @param id: int
     * @param dungeon: Dungeon
     * 
     * Constructor for a Sword Treasure with Integer ID `id` and the Dungeon.
     */
    public Sword(int id, Dungeon dungeon) {
        setLocation(dungeon); // Game Dungeon
        this.ownerFightBonus = 1; // Swords give a Character strength buff of 1
        this.treasureType = "Sword"; // String name
    }
}
