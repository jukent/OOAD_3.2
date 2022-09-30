package Treasure;

import Dungeon.Dungeon;

public class Trap extends Treasure {


    /**
     * @param ID
     * @param dungeon
     */
    public Trap(int ID, Dungeon dungeon) {
        setLocation(dungeon);
        this.TakeDamage = 1;
        this.TreasureType = "Trap";
    }
}
