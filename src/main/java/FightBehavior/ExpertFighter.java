package FightBehavior;

import Util.DiceRolls;

public class ExpertFighter extends FightBehavior {


    /**
     * 
     */
    public ExpertFighter() {
        this.FightType = "Expert";
    }
    

    /* (non-Javadoc)
     * @see FightBehavior.FightBehavior#fight()
     */
    public int fight() {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 2;
    }
}
