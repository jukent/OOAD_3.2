package main.java;
public abstract class Celebration extends FightBehavior {

    protected Celebration celebrationRef;
    protected FightBehavior fightRef;

    public abstract int fight();
    public void celebrate() {}
    
}
