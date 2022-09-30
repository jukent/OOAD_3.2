package FightBehavior;

import Util.DiceRolls;

public class TrainedFighter extends FightBehavior {


    /**
     * 
     */
    public TrainedFighter() {
        this.FightType = "Trained";
    }

    /* (non-Javadoc)
     * @see FightBehavior.FightBehavior#fight()
     */
    public int fight() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;
    }
}
