package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class DanceCelebration extends Celebration {

    // Subclass of a decorator pattern


    /**
     * @param fight FightBehavior
     *
     * Dance Celebration constructor.
     */
    public DanceCelebration(FightBehavior fight) {
        super(fight);
    }


     /**
     * @param celebrateRef Celebration
     *
     * Dance Celebration constructor.
     */
    public DanceCelebration(Celebration celebrateRef) {
        super(celebrateRef);
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#fight()
     * @return int
     *
     * Implements the "fight" dice-roll integer.
     */
    @Override
    public int fight() {
        return this.fightRef.fight();
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#celebrate()
     *
     * This implementation executes dancing celebration a random number of times.
     */
    @Override
    public void celebrate() {
        this.fightRef.celebrate();
        for (int i = 0; i < DiceRolls.rollDice(ROLL_NUMBER) - 1; i++) {
            System.out.print("Dance! ");
        }
    }
}
