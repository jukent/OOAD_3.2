public class MovementBehavior {
 
    protected String MovementType;

    /**
     * @param Type: String
     * 
     * Move Constructor
     */
    public MovementBehavior(String Type){
        MovementType = Type;
    }


    /**
     * This method executes movement based on the 4 main movement types.
     */
    public void move() {
        if (MovementType == "Character") {}
        if (MovementType == "Blinker") {}
        if (MovementType == "Seeker") {}
        if (MovementType == "Orbiter") {}
    }
}
