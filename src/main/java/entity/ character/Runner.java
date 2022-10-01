package entity.character;

import dungeon.Dungeon;
import fight.UntrainedFighter;
import treasurehunt.QuickHunt;

public class Runner extends Character { 
    // Example of inheritance


    /**
     * @param id: int
     * @param map: Dungeon
     * 
     * Constructs a runner with an Integer `id` and the Dungeon.
     */
    public Runner (int id, Dungeon map) {
        super.id =id; // Runner ID value
        this.dungeon = map; // Game Dungeon
        this.location = dungeon.getRoom("(0-1-1)"); // Begin in Entrance Room
        setMoveCount(2); // Int moveCount of 2, Runner's get twice the turns, 
        this.fightBehavior = new UntrainedFighter(); // FightType is Untrained
        this.searchBehavior = new QuickHunt(); // SearchType is Quick
        name = "Runner"; // String name
    }
}
