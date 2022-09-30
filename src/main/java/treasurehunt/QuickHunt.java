package treasurehunt;

import util.DiceRolls;

public class QuickHunt extends TreasureHunt{
    

    /**
     * Constructor for a Quick Treasure Hunt.
     */
    public QuickHunt(){
        this.searchType = "Quick"; // String name
        this.neededScore = 9; // Required dice roll of 9
    }


    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     * 
     * Overwrites the abstract treasure hunting with a Quick Hunt.
     */
    @Override
    public int searchTreasure() {
        if (DiceRolls.rollDice(3) == 1) {
            return -1;
        } else {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
    }
}
