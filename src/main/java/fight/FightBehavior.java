package fight;

public abstract class FightBehavior {

    public String fightType; // String description of Fight Behavior


    /**
     * @return int
     * 
     * This method executes the fight by rolling two dice and adding any extra strength buffs.
     */
    public abstract int fight();


    /**
     * 
     */
    public void celebrate() {};


    /**
     * @return String
     * 
     * Returns the type of FightBehavior (i.e. "Creature", "Expert", "Stealthy", "Trained", "Untrained").
     */
    public String getFightType() {
        return this.fightType;
    }


    /**
     * @param fightType: String
     * 
     * Sets the FightBehavior type.
     */
    public void setFightType(String fightType) {
        this.fightType = fightType;
    }
}
