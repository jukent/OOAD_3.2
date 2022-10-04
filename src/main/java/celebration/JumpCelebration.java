package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class JumpCelebration extends Celebration {
    
    // Subclass of a decorator pattern


    /**
     * @param fight FightBehavior
     * 
     * Jump Celebration constructor.
     */
    public JumpCelebration(FightBehavior fight) {
        this.fightRef = fight;
    }


     /**
     * @param celebrateRef Celebration
     * 
     * Jump Celebration constructor.
     */
    public JumpCelebration(Celebration celebrateRef) {
        this.fightRef = celebrateRef.fightRef;
        this.celebrationRef = celebrateRef;
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#fight()
     * @return int
     * 
     * Returns the "fight" dice-roll integer.
     */
    public int fight() {
        return this.fightRef.fight();
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#celebrate()
     * 
     * Executes jumping celebration a random number of times.
     */
    @Override
    public void celebrate() {
        if (this.celebrationRef != null) {
            this.celebrationRef.celebrate();
        }
        for (int i = 0; i < DiceRolls.rollDice(ROLL_NUMBER) - 1; i++) {
            System.out.print("Jump! ");
        }
    }
}
