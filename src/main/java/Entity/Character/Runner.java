package Entity.Character;

import Dungeon.Dungeon;
import FightBehavior.UntrainedFighter;
import TreasureHuntBehavior.QuickHunt;

public class Runner extends Character { 
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Constructs a runner with ID `A` and the Dungeon.
     */
    public Runner (int A, Dungeon map) {
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        super.ID = A;
        setMoveCount(2);
        this.FightBehavior = new UntrainedFighter();
        this.HuntBehavior = new QuickHunt();
        name = "Runner";
    }
}
