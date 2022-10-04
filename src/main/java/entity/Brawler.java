package entity;

import dungeon.Dungeon;
import fight.ExpertFightBehavior;
import treasurehunt.CarelessHuntBehavior;

public class Brawler extends Character { 
    // Example of inheritance
    
    
    /**
     * @param id: int
     * @param map: Dungeon
     * 
     * Brawler constructor must be passed in an integer ID `id` and the Dungeon.
     * Brawler is constructed with starting room.
     */
    public Brawler(int id, Dungeon map) {
        super.id = id; // Brawler ID value
        this.dungeon = map; // Game Dungeon
        this.location = dungeon.getRoom("(0-1-1)"); // Begin in Entrance Room
        this.fightBehavior = new ExpertFightBehavior(); // FightType is Expert
        this.searchBehavior = new CarelessHuntBehavior(); // SearchType is Careless
        name = "Brawler"; // String name
    }
}
