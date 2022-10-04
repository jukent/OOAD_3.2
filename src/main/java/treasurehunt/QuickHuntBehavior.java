package treasurehunt;

import util.DiceRolls;

public class QuickHuntBehavior extends TreasureHuntBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.

    protected final static int NEEDEDSCORE = 9;
    protected final static int THREESIDES = 3;
    protected final static int DICESIDES = 6;


    /**
     * Constructor for a Quick Treasure Hunt.
     */
    public QuickHuntBehavior() {
        setSearchType("Quick"); // String name
        setNeededScore(NEEDEDSCORE); // Required dice roll of 9
    }


    /* (non-Javadoc)
     * @see treasurehunt.TreasureHunt#searchTreasure()
     *
     * Overwrites the abstract treasure hunting with a Quick Hunt.
     */
    @Override
    public int searchTreasure() {
        if (DiceRolls.rollDice(THREESIDES) == 1) {
            return -1;
        } else {
            return DiceRolls.rollDice(DICESIDES) + DiceRolls.rollDice(DICESIDES);
        }
    }
}
