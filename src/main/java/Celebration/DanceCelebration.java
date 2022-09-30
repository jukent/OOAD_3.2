package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class DanceCelebration extends Celebration {


    /**
     * @param fight: FightBehavior
     * 
     * Dance Celebration constructor.
     */
    public DanceCelebration(FightBehavior fight) {
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
     * Executes dancing celebration a random number of times.
     */
    public void celebrate() {
        this.fightRef.celebrate();
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            System.out.print("Dance! ");
        }
    }
}
