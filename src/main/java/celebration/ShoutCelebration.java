package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class ShoutCelebration extends Celebration {


    /**
     * @param fight
     * 
     * Shout Celebration constructor.
     */
    public ShoutCelebration(FightBehavior fight) {
        this.fightRef = fight;
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
    public void celebrate() {
        this.fightRef.celebrate();
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            System.out.print("Shout! ");
        }
    }
}
