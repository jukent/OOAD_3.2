package main.java;
public abstract class FightBehavior {

    public String FightType;


    /**
     * @return int
     *
     * This method executes the fight by rolling two dice and adding any extra strength buffs.
     */
    public abstract int fight();

}
