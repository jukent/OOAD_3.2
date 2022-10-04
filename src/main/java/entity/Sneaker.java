package entity;

import dungeon.Dungeon;
import fight.StealthyFightBehavior;
import treasurehunt.QuickHuntBehavior;

public class Sneaker extends Character { 
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     *
     * Construct Sneakers with an Integer ID `id` and the Dungeon
     */
    public Sneaker(int id, Dungeon map) {
        setID(id); // Sneaker ID value
        this.dungeon = map; // Game Dungeon
        this.location = dungeon.getRoom("(0-1-1)"); // Begin in Entrance Room
        this.fightBehavior = new StealthyFightBehavior(); // FightType is Stealthy
        setSearchBehavior(new QuickHuntBehavior()); // SearchType is Quick
        name = "Sneaker"; // Sring name
    }
}
