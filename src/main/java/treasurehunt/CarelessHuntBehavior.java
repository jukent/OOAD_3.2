package treasurehunt;

import util.DiceRolls;

public class CarelessHuntBehavior extends TreasureHuntBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    /**
     * Constructor for a Careless Treasure Hunt.
     */
    public CarelessHuntBehavior() {
        this.searchType = "Careless"; // String name
        this.neededScore = 10; // Required Dice Roll of 10
    }

    
    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     * 
     * Overwrites the abstract treasure hunting with a Careless Hunt.
     */
    @Override
    public int searchTreasure() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    } 
}
