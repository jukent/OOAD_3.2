package celebration;

import fight.FightBehavior;

/**
 * Abstract class for celebration decorators of fight behaviors.
 */
public abstract class Celebration extends FightBehavior {

    private Celebration celebrationRef;
    private FightBehavior fightRef;

    protected static final int ROLL_NUMBER = 3;

    // This is our decorator pattern.
    // The celebration decorator decorates the fight behaviors
    // and can inherit fight references and celebration references
    // to modify the behavior of fight behavior using the same methods.


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


    /**
     * @return Celebration
     *
     * Returns the Celebration reference.
     */
    public Celebration getCelebrationRef() {
        return celebrationRef;
    }


    /**
     * @param celebrationRef Celebration
     *
     * Sets the Celebration reference.
     */
    public void setCelebrationRef(Celebration celebrationRef) {
        this.celebrationRef = celebrationRef;
    }



    /**
     * @return FightBehavior
     *
     * Returns the decorated Fight Behavior.
     */
    public FightBehavior getFightRef() {
        return fightRef;
    }


    /**
     * @param fightRef FightBehavior
     *
     * Sets the decorated Fight Behavior.
     */
    public void setFightRef(FightBehavior fightRef) {
        this.fightRef = fightRef;
    }
}
