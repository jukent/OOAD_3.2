package treasure;

import dungeon.Dungeon;

public class Gem extends Treasure {


    /**
     * @param id: int
     * @param dungeon: Dungeon
     * 
     * Constructor for a Gem Treasure with Integer ID `id` and the Dungeon.
     */
    public Gem(int ID, Dungeon dungeon) { 
        setLocation(dungeon); // Game Dungeon
        this.adversaryFightBonus = 1; // Gems give a Creature a strength buff of 1
        this.treasureType = "Gem"; // String name
    }
}
