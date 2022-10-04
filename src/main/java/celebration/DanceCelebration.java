package celebration;

import fight.FightBehavior;
import util.DiceRolls;

public class DanceCelebration extends Celebration {

    // Subclass of a decorator pattern


    /**
     * @param fight: FightBehavior
     * 
     * Dance Celebration constructor.
     */
    public DanceCelebration(FightBehavior fight) {
        this.fightRef = fight;
    }


     /**
     * @param celebrateRef: Celebration
     * 
     * Dance Celebration constructor.
     */
    public DanceCelebration(Celebration celebrateRef) {
        this.fightRef = celebrateRef.fightRef;
        this.celebrationRef = celebrateRef;
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#fight()
     * @return int
     * 
     * Returns the "fight" dice-roll integer.
     */
    @Override
    public int fight() {
        return this.fightRef.fight();
    }


    /* (non-Javadoc)
     * @see celebration.Celebration#celebrate()
     * 
     * Executes dancing celebration a random number of times.
     */
    @Override
    public void celebrate() {
        if(this.celebrationRef != null){this.celebrationRef.celebrate();}
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            System.out.print("Dance! ");
        }
    }

    public String trackCelebrate(){
        String results = "";
        if(this.celebrationRef != null){Results += this.celebrationRef.trackCelebrate();}
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++) {
            Results += "Dance! ";
        }
        return Results;
    }
}
