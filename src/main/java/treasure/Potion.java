package treasure;

import dungeon.Dungeon;

public class Potion extends Treasure {


    /**
     * @param id int
     * @param dungeon Dungeon
     *
     * Constructor for a Potion Treasure with Integer ID `id` and the Dungeon.
     */
    public Potion(int id, Dungeon dungeon) {
        hide(dungeon); // Game Dungeon
        this.hpBoost = 1; // Potions restore 1 health point 
        this.treasureType = "Potion"; // String name
    }
}
