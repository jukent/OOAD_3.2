package FightBehavior;

import Util.DiceRolls;

public class CreatureFighter extends FightBehavior {


    /**
     * 
     */
    public CreatureFighter() {
        this.FightType = "Creature";
    }
    
        
    /* (non-Javadoc)
     * @see FightingBehavior.FightBehavior#fight()
     */
    public int fight() {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }
}
