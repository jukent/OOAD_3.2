package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class SpinCelebration extends Celebration {

    // Subclass of a decorator pattern


    /**
     * @param fight FightBehavior
     *
     * Spin Celebration constructor.
     */
    public SpinCelebration(FightBehavior fight) {
        setFightRef(fight);
    }


     /**
     * @param celebrateRef Celebration
     *
     * Spin Celebration constructor.
     */
    public SpinCelebration(Celebration celebrateRef) {
        setFightRef(celebrateRef.getFightRef());
        setCelebrationRef(celebrateRef);
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#fight()
     * @return int
     *
     * Returns the "fight" dice-roll integer.
     */
    public int fight() {
        return this.getFightRef().fight();
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#celebrate()
     *
     * Executes spinning celebration a random number of times.
     */
    @Override
    public void celebrate() {
        if (this.getCelebrationRef() != null) {
            this.getCelebrationRef().celebrate();
        }
        for (int i = 0; i < DiceRolls.rollDice(ROLL_NUMBER) - 1; i++) {
            System.out.print("Spin! ");
        }
    }
}
