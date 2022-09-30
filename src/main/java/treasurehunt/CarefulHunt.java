package treasurehunt;

import util.DiceRolls;

public class CarefulHunt extends TreasureHunt {
    

    /**
     * 
     */
    public CarefulHunt() {
        this.SearchType = "Careful";
        this.NeededScore = 7;
    }


    /* (non-Javadoc)
     * @see TreasureHuntBehavior.TreasureHunt#searchTreasure()
     */
    public int searchTreasure() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }
}
