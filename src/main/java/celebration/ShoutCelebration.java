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
        this.fightRef = fight;
    }


     /**
     * @param celebrateRef: Celebration
     * 
     * Shout Celebration constructor.
     */
    public ShoutCelebration(Celebration celebrateRef) {
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
     * Executes shouting celebration a random number of times.
     */
    @Override
    public void celebrate() {
        if(this.celebrationRef != null){this.celebrationRef.celebrate();}
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            System.out.print("Shout! ");
        }
    }

    public String trackCelebrate() {
        String results = "";
        if (this.celebrationRef != null) {
            results += this.celebrationRef.trackCelebrate();
        }
        for (int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            Results += "Shout! ";
        }
        return Results;
    }
}
