package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class SpinCelebration extends Celebration {

    //Subclass of a decorator pattern

    /**
     * @param fight: FightBehavior
     * 
     * Dance Celebration constructor.
     */
    public SpinCelebration(FightBehavior fight) {
        super(fight)
    }

     /**
     * @param celebrateRef: Celebration
     * 
     * Dance Celebration constructor.
     */
    public SpinCelebration(Celebration celebrateRef) {
        super(celebrateRef)
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
     * Executes spinning celebration a random number of times.
     */
    public void celebrate() {
        this.fightRef.celebrate();
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            System.out.print("Spin! ");
        }
    }
}
