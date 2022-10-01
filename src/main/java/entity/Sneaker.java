package entity;

import dungeon.Dungeon;
import fight.StealthyFighter;
import treasurehunt.QuickHunt;

public class Sneaker extends Character { 
    // Example of inheritance


    /**
     * @param id: int
     * @param map: Dungeon
     * 
     * Construct Sneakers with an Integer ID `id` and the Dungeon
     */
    public Sneaker(int id, Dungeon map) {
        super.id = id; // Sneaker ID value
        this.dungeon = map; // Game Dungeon
        this.location = dungeon.getRoom("(0-1-1)"); // Begin in Entrance Room
        this.fightBehavior = new StealthyFighter(); // FightType is Stealthy
        this.searchBehavior = new QuickHunt(); // SearchType is Quick
        name = "Sneaker"; // Sring name
    }
}
