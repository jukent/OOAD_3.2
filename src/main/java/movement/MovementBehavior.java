package movement;

import dungeon.Dungeon;
import entity.Entity;

public abstract class MovementBehavior {
 
    private String movementType;

    
    /**
     * Movement Constructor.
     */
    public MovementBehavior() {}


    /**
     * @return String
     * 
     * Returns the MovementBehavior type (e.g. "Blink", "Orbit", "RandomWalk", "Seek").
     */
    public String getMovementType() {
        return this.movementType;
    }


    /**
     * @param movementType
     * 
     * Sets the MovementBehavior type.
     */
    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }


    /**
     * @param entity
     * @param dungeon
     * 
     * Abstract method for Entity movement.
     */
    public abstract void move(Entity entity, Dungeon dungeon);
}
