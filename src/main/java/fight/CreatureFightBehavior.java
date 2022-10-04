package fight;

import util.DiceRolls;

public class CreatureFightBehavior extends FightBehavior {


    /**
     * Creature Fighter constructor.
     */
    public CreatureFightBehavior() {
        setFightType("Creature");
    }
    
        
    /* (non-Javadoc)
     * @see FightingBehavior.FightBehavior#fight()
     * 
     * @return int
     * 
     * Returns the dice roll for a Creature Fighter (no strength buff).
     */
    public int fight() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }
}