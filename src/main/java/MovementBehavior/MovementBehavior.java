package MovementBehavior;

import Dungeon.Dungeon;
import Entity.Entity;

public abstract class MovementBehavior {
 
    private String MovementType;

    
    /**
     * @param Type: String
     * 
     * Move Constructor
     */
    public MovementBehavior() {}


    /**
     * @return
     */
    public String getMovementType() {
        return MovementType;
    }


    /**
     * @param movementType
     */
    public void setMovementType(String movementType) {
        this.MovementType = movementType;
    }


    /**
     * @param entity
     * @param dungeon
     */
    public abstract void move(Entity entity, Dungeon dungeon);
}
