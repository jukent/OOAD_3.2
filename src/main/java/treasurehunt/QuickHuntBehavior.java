package treasurehunt;

import util.DiceRolls;

public class QuickHuntBehavior extends TreasureHuntBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.


    /**
     * Constructor for a Quick Treasure Hunt.
     */
    public QuickHuntBehavior() {
        setSearchType("Quick"); // String name
        final int NEEDEDSCORE = 9;
        setNeededScore(NEEDEDSCORE); // Required dice roll of 9
    }


    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     *
     * Overwrites the abstract treasure hunting with a Quick Hunt.
     */
    @Override
    public int searchTreasure() {
        final int THREESIDES = 3;
        if (DiceRolls.rollDice(THREESIDES) == 1) {
            return -1;
        } else {
            final int DICESIDES = 6;
            return DiceRolls.rollDice(DICESIDES) + DiceRolls.rollDice(DICESIDES);
        }
    }
}
