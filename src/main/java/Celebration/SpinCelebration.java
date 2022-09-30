package Celebration;

import FightingBehavior.FightBehavior;
import Util.DiceRolls;

public class SpinCelebration extends Celebration {


    /**
     * @param fight
     */
    public SpinCelebration(FightBehavior fight) {
        this.fightRef = fight;
    }


    /* (non-Javadoc)
     * @see Celebration.Celebration#fight()
     */
    public int fight() {
        return this.fightRef.fight();
    }


    /* (non-Javadoc)
     * @see Celebration.Celebration#celebrate()
     */
    public void celebrate() {
        this.fightRef.celebrate();
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            System.out.print("Spin! ");
        }
    }
}
