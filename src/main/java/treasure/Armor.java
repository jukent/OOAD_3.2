package treasure;

import dungeon.Dungeon;

public class Armor extends Treasure {


    /**
     * @param id int
     * @param dungeon Dungeon
     *
     * Constructor for an Armor treasure with Integer ID `id` and the Dungeon.
     */
    public Armor(int id, Dungeon dungeon) {
        hide(dungeon); // Game Dungeon
        this.adversaryFightBonus = -1; // Armor provides defense by giving Creature's a stength buff of -1
        this.treasureType = "Armor"; // String name
    }
}
