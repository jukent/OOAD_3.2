package fight;

import util.DiceRolls;

public class StealthyFighter extends FightBehavior {


    /**
     * Constructor for a Stealthy Fighter.
     */
    public StealthyFighter() {
        setFightType("Stealth");
    }
    
    /* (non-Javadoc)
     * @see FightBehavior.FightBehavior#fight()
     * 
     * @return int
     * 
     * Returns the dice roll for a Stealthy Fighter (strength buff of 1).
     * Stealthy fighters havea  50% chance of avoiding a fight, indicated by a return value of -1.
     */
    public int fight() {
        if (DiceRolls.rollDice(2) == 1) {
            return -1;
        } else {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;
        }
    }
}
