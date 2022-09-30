public class Sneakers extends Character { 
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Construct Sneakers with ID `A` and the Dungeon
     */
    Sneakers(int A, Dungeon map) {
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.FightBehavior = new StealthyFighter();
        this.HuntBehavior = new QuickHunt();
        super.ID = A;
        checkPortalInInventory();
        name = "Sneaker";
    }
}
