public class Thieves extends Characters {
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Construct Thieves with ID `A` and the Dungeon
     */
    Thieves(int A, Dungeon map) {
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.HuntBehavior = new CarefulHunt();
        this.FightBehavior = new TrainedFighter();
        super.ID = A;
        name = "Thief";
    }
}
