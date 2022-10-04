package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class SpinCelebration extends Celebration {

    // Subclass of a decorator pattern


    /**
     * @param fight: FightBehavior
     * 
     * Spin Celebration constructor.
     */
    public SpinCelebration(FightBehavior fight) {
        this.fightRef = fight;
    }


     /**
     * @param celebrateRef: Celebration
     * 
     * Spin Celebration constructor.
     */
    public SpinCelebration(Celebration celebrateRef) {
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
     * Executes spinning celebration a random number of times.
     */
    @Override
    public void celebrate() {
        if(this.celebrationRef != null){this.celebrationRef.celebrate();}
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            System.out.print("Spin! ");
        }
    }

    public String trackCelebrate() {
        String results = "";
        if(this.celebrationRef != null){Results += this.celebrationRef.trackCelebrate();}
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            Results += "Spin! ";
        }
        return results;
    }
}
