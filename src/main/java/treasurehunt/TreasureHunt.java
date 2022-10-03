package treasurehunt;
public abstract class TreasureHunt {
    
    //Implementation of the strategy OOP Design pattern. Subclasses extend
    //This behavior

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


    /**
     * @return String
     * 
     * Returns the String Treasure Hunt type for a Hunt behavior.
     */
    public String getType() {
        return this.searchType;
    }
}
