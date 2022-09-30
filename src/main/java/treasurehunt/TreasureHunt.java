package treasurehunt;
public abstract class TreasureHunt {
    
    public String searchType; // String type for Treasue Hunting Behavior
    public int neededScore; // Required Integer dice roll to find treasure


    /**
     * @return int
     * 
     * This abstract method searches for treasure by rolling dice and returns the "dice roll" integer.
     */
    public abstract int searchTreasure();


    /**
     * @return int
     * 
     * Returns the needed dice roll for a Treasure Search behavior.
     */
    public int getNeededScore() {
        return this.neededScore;
    }
}
