package main.java;
public class Portal extends Treasure {

    /**
     * @param ID
     * @param dungeon
     */
    public Portal(int ID, Dungeon dungeon) {
        setLocation(dungeon);  
        this.TreasureType = "Portal";
        //this.OwnerMovementBehavior = new BlinkMovement();
    }
}
