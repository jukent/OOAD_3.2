package treasure;

import dungeon.Dungeon;

public class Portal extends Treasure {

    
    /**
     * @param id: int
     * @param dungeon: Dungeon
     * 
     * Constructor for a Portal treasure with Integer ID `id` and the Dungeon.
     * 
     * Portals allow (read: make) Characters Blink instead of Random Walk.
     * Portal is activated by checking for its type in a Character's Treasure inventory before movement.
     */
    public Portal(int id, Dungeon dungeon) {
        setLocation(dungeon);  // Game Dungeon
        this.treasureType = "Portal"; // String name
    }
}
