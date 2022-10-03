package fight;

import util.DiceRolls;

public class UntrainedFighter extends FightBehavior {
    
        //Implementation of the strategy OOP Design pattern. This is a subclass
    //of the strategy pattern

    /**
     * Constructor for an Untrained Fighter.
     */
    public UntrainedFighter() {
        setFightType("Untrained");
    }
    

    /* (non-Javadoc)
     * @see FightingBehavior.FightBehavior#fight()
     * 
     * @return int
     * 
     * Returns the dice roll for an Untrained Fighter (no strength buff).
     */
    public int fight() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }
}
