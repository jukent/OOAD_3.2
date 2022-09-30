package Treasure;

import Dungeon.Dungeon;

public class Gem extends Treasure {


    /**
     * @param ID
     */
    public Gem(int ID, Dungeon dungeon) {
        this.AdversaryFightBonus = 1;
        this.TreasureType = "Gem";
        setLocation(dungeon);
    }
}
