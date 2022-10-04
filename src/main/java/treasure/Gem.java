package treasure;

import dungeon.Dungeon;

public class Gem extends Treasure {


    /**
     * @param id int
     * @param dungeon Dungeon
     *
     * Constructor for a Gem Treasure with Integer ID `id` and the Dungeon.
     */
    public Gem(int id, Dungeon dungeon) {
        hide(dungeon); // Game Dungeon
        setAdversaryFightBonus(1); // Gems give a Creature a strength buff of 1
        setTreasureType("Gem"); // String name
    }
}
