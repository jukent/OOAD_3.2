public abstract class MovementBehavior {
 
    protected String MovementType;

    /**
     * @param Type: String
     * 
     * Move Constructor
     */
    public MovementBehavior() {
    }


    public abstract void move(Entities entity, Dungeon dungeon);
}
