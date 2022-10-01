package entity;

public abstract class Creature extends Entity {

    protected int hp = 1; // Health Point integer is 1, one-hit-kills
    protected int moveCount = 1; // Integer movement count


    /**
     * The abstract method sets a Creature's starting room
     * to be overwritten with more specific Creature behavior.
     */
    protected abstract void setStartingRoom();
}
