package Entity.Character;
import java.util.ArrayList;

import Entity.Entity;
import MovementBehavior.BlinkMovement;
import Treasure.Treasure;
import TreasureHuntBehavior.TreasureHunt;

public abstract class Character extends Entity {

    public TreasureHunt HuntBehavior;
    public ArrayList<Treasure> Inventory = new ArrayList<Treasure>();
    public ArrayList<String> InventoryTypes = new ArrayList<String>();

    private int MoveCount = 1;


    /**
     * 
     */
    public void checkPortalInInventory() {
        ArrayList<String> inventory_types =  getInventoryTypes();
        if (inventory_types.contains("Portal")) {
            BlinkMovement blink_movement_behavior = new BlinkMovement();
            setMovementBehavior(blink_movement_behavior);
        }
    }


    /**
     * @return
     */
    public int getMoveCount() {
        return MoveCount;
    }


    /**
     * @param moveCount
     */
    public void setMoveCount(int moveCount) {
        this.MoveCount = moveCount;
    }


    /**
     * @return int
     * 
     * This abstract method calls a Character's treasure hunting behavior
     * and returns their "treasure roll" as an integer.
     */
    public int searchTreasure() {
        return HuntBehavior.searchTreasure();
    }


    /**
     * @param HP
     */
    public void addHealth(int HP){
        this.HP += HP;
    }

    
    /**
     * @return
     */
    public ArrayList<Treasure> getInventory() {
        return this.Inventory;
    }


    /**
     * @param treasure
     */
    public void addInventory(Treasure treasure) {
        ArrayList<Treasure> inventory = this.getInventory();
        inventory.add(treasure);

        ArrayList<String> InventoryTypes = getInventoryTypes();
        InventoryTypes.add(treasure.getType());
        setInventoryTypes(InventoryTypes);
    }


    /**
     * @return
     */
    public ArrayList<String> getInventoryTypes() {
        return InventoryTypes;
    }


    /**
     * @param inventoryTypes
     */
    public void setInventoryTypes(ArrayList<String> inventoryTypes) {
        this.InventoryTypes = inventoryTypes;
    }


    /**
     * @return
     */
    public String getInventoryString() {
        // Doesn't display Trap multiple times if the same adventurer encountered multiple traps?
        ArrayList<String> str_array =  this.InventoryTypes;
        String inv_str = String.join(", ", str_array);
        return inv_str;
    }
}
