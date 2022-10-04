package entity;

import dungeon.Dungeon;
import fight.UntrainedFightBehavior;
import treasurehunt.QuickHuntBehavior;

public class Runner extends Character { 
    // Example of inheritance


    /**
     * @param id int
     * @param map Dungeon
     *
     * Constructs a runner with an Integer `id` and the Dungeon.
     */
    public Runner (int id, Dungeon map) {
        setID(id); // Runner ID value
        this.dungeon = map; // Game Dungeon
        this.location = dungeon.getRoom("(0-1-1)"); // Begin in Entrance Room
        setMoveCount(2); // Int moveCount of 2, Runner's get twice the turns, 
        this.fightBehavior = new UntrainedFightBehavior(); // FightType is Untrained
        this.setSearchBehavior(new QuickHuntBehavior()); // SearchType is Quick
        name = "Runner"; // String name
    }
}
