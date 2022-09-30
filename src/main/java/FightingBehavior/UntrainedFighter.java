package FightingBehavior;

import Util.DiceRolls;

public class UntrainedFighter extends FightBehavior {
    
    
    /**
     * 
     */
    public UntrainedFighter() {
        this.FightType = "Untrained";
    }
    

    /* (non-Javadoc)
     * @see FightingBehavior.FightBehavior#fight()
     */
    public int fight() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }
}
