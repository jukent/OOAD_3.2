package treasure;

import dungeon.Dungeon;

public class Portal extends Treasure {

    
    /**
     * @param ID
     * @param dungeon
     */
    public Portal(int ID, Dungeon dungeon) {
        setLocation(dungeon);  
        this.TreasureType = "Portal";
    }
}