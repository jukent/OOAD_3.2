package main.java;
public class Runner extends Character { 
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Constructs a runner with ID `A` and the Dungeon.
     */
    Runner (int A, Dungeon map) {
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        super.ID = A;
        super.MoveCount = 2;
        this.FightBehavior = new UntrainedFighter();
        this.HuntBehavior = new QuickHunt();
        checkPortalInInventory();
        name = "Runner";
    }
}
