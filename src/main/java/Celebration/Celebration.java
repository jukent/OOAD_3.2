package Celebration;

import FightingBehavior.FightBehavior;

public abstract class Celebration extends FightBehavior {

    protected Celebration celebrationRef;
    protected FightBehavior fightRef;

    public abstract int fight();
    public void celebrate() {}
}
