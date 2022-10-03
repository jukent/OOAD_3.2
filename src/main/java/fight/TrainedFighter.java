package fight;

import util.DiceRolls;

public class TrainedFighter extends FightBehavior {

    // Implementation of the strategy OOP Design pattern. This is a subclass
    // of the strategy pattern.

    
    /**
     * Constructor for a Trained Fighter.
     */
    public TrainedFighter() {
        setFightType("Trained");
    }

    /* (non-Javadoc)
     * @see FightBehavior.FightBehavior#fight()
     * 
     * @return int
     * 
     * Returns the dice roll for a Trained Fighter (strength buff of 1).
     */
    public int fight() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;
    }
}
