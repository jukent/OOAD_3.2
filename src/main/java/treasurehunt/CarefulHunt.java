package treasurehunt;

import util.DiceRolls;

public class CarefulHunt extends TreasureHunt {
    

    /**
     * Constructor for a Careful Treasure Hunt.
     */
    public CarefulHunt() {
        this.searchType = "Careful"; // String Name
        this.neededScore = 7; // Required dice roll of Integer 7
    }


    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     * 
     * Overwrides the abstract treasure hunting with a Careful Hunt.
     */
    @Override
    public int searchTreasure() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }
}
