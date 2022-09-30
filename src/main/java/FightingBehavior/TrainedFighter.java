package FightingBehavior;

import Util.DiceRolls;

public class TrainedFighter extends FightBehavior {


    /**
     * 
     */
    public TrainedFighter() {
        this.FightType = "Trained";
    }

    /* (non-Javadoc)
     * @see FightingBehavior.FightBehavior#fight()
     */
    public int fight() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;
    }
}
