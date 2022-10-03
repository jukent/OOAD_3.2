package celebration;

import fight.FightBehavior;

public abstract class Celebration extends FightBehavior {

    protected Celebration celebrationRef;
    protected FightBehavior fightRef;


    // This is our decorator pattern. The celebration decorator decorates the fight behaviors
    // and can inherit fight references and celebration references to modify the behavior of
    // fight behavior using the same methods.


    /**
     * @param fight: FightBehavior
     * 
     * Celebration constructor.
     */

    public Celebration(FightBehavior fight) {
        this.fightRef = fight;
    }


    /**
     * @param celebrateRef: Celebration
     * 
     * Dance Celebration constructor.
     */

    public Celebration(Celebration celebrateRef) {
        this.fightRef = celebrateRef.fightRef;
        this.celebrationRef = celebrateRef;
    }


    /* (non-Javadoc)
     * @see fight.FightBehavior#fight()
     * @return int
     * 
     * Abstract method returning a "fight" dice-roll integer.
     */
    public abstract int fight();


    /* (non-Javadoc)
     * @see fight.FightBehavior#celebrate()
     * 
     * Abstract celebration method.
     */
    public abstract void celebrate();
}
