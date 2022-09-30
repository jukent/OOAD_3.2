package TreasureHuntBehavior;

import Util.DiceRolls;

public class QuickHunt extends TreasureHunt{
    

    /**
     * 
     */
    public QuickHunt(){
        this.SearchType = "Quick";
        this.NeededScore = 9;
    }


    /* (non-Javadoc)
     * @see TreasureHuntBehavior.TreasureHunt#searchTreasure()
     */
    public int searchTreasure() {
        if (DiceRolls.rollDice(3) == 1) {
            return -1;
        } else {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
    }
}
