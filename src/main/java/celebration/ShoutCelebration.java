package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class ShoutCelebration extends Celebration {

    // Subclass of a decorator pattern


    /**
     * @param fight: FightBehavior
     * 
     * Shout Celebration constructor.
     */
    public ShoutCelebration(FightBehavior fight) {
        super(fight);
    }


     /**
     * @param celebrateRef: Celebration
     * 
     * Shout Celebration constructor.
     */
    public ShoutCelebration(Celebration celebrateRef) {
        super(celebrateRef);
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
     * Executes shouting celebration a random number of times.
     */
    @Override
    public void celebrate() {
        this.fightRef.celebrate();
        for(int i = 0; i < DiceRolls.rollDice(ROLL_NUMBER) - 1; i++) {
            System.out.print("Shout! ");
        }
    }
}
