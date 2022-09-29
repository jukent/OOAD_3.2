package main.java;
public abstract class MovementBehavior {
 
    protected String MovementType;

    /**
     * @param Type: String
     * 
     * Move Constructor
     */
    public MovementBehavior() {
    }

    /**
     * @param entity
     * @param dungeon
     */
    public abstract void move(Entity entity, Dungeon dungeon);
}
