package entity;

import dungeon.Dungeon;
import fight.TrainedFightBehavior;
import treasurehunt.CarefulHuntBehavior;

public class Thief extends Character {
    // Example of inheritance


    /**
     * @param id: int
     * @param map: Dungeon
     * 
     * Construct Thieves with an Integer ID `id` and the Dungeon
     */
    public Thief(int id, Dungeon map) {
        super.id = id; // Thief ID value
        this.dungeon = map; // Game Dungeon
        this.location = dungeon.getRoom("(0-1-1)"); // Start in Entrance Room
        this.fightBehavior = new TrainedFightBehavior(); // FightType is Trained
        this.searchBehavior = new CarefulHuntBehavior(); // SearhType is Careful
        name = "Thief"; // String name
    }
}
