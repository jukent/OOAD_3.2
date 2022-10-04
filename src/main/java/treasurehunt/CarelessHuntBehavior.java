package treasurehunt;

import util.DiceRolls;

public class CarelessHuntBehavior extends TreasureHuntBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    /**
     * Constructor for a Careless Treasure Hunt.
     */
    public CarelessHuntBehavior() {
        setSearchType("Careless"); // String name
        final int NEEDEDSCORE = 10;
        setNeededScore(NEEDEDSCORE); // Required Dice Roll of 10
    }


    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     *
     * Overwrites the abstract treasure hunting with a Careless Hunt.
     */
    @Override
    public int searchTreasure() {
        final int DICESIDES = 6;
        return DiceRolls.rollDice(DICESIDES) + DiceRolls.rollDice(DICESIDES);
    } 
}
