package entity;

public abstract class Creature extends Entity {


    /**
     * Constructor for a Creature.
     */
    public Creature() {
        this.setHP(1); // Health Point integer is 1, one-hit-kills
    }

    /**
     * The abstract method sets a Creature's starting room
     * to be overwritten with more specific Creature behavior.
     */
    abstract void setStartingRoom();
}
