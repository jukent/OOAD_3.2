package TreasureHuntBehavior;

import Util.DiceRolls;

public class CarelessHunt extends TreasureHunt {


    /**
     * 
     */
    public CarelessHunt() {
        this.SearchType = "Careless";
        this.NeededScore = 10;
    }

    
    /* (non-Javadoc)
     * @see TreasureHuntBehavior.TreasureHunt#searchTreasure()
     */
    public int searchTreasure() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    } 
}
