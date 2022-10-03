package celebration;

import fight.FightBehavior;

public abstract class Celebration extends FightBehavior {

    protected Celebration celebrationRef;
    protected FightBehavior fightRef;

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
    public void celebrate() {};
}
