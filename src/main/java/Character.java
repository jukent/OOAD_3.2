package main.java;
import java.util.ArrayList;

public abstract class Character extends Entity {

    public TreasureHunt HuntBehavior;
    public ArrayList<Treasure> Inventory = new ArrayList<Treasure>();
    public ArrayList<String> InventoryTypes = new ArrayList<String>();

    protected int MoveCount = 1;


    public void checkPortalInInventory() {
        ArrayList<String> inventory_types =  getInventoryTypes();
        if (inventory_types.contains("Portal")) {
            BlinkMovement blink_movement_behavior = new BlinkMovement();
            setMovementBehavior(blink_movement_behavior);
        }
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


    public void addHealth(int HP){
        this.HP += HP;
    }

    
    public ArrayList<Treasure> getInventory() {
        return this.Inventory;
    }


    public void setInventory(Treasure treasure) {
        ArrayList<Treasure> inventory = this.getInventory();
        inventory.add(treasure);

        ArrayList<String> InventoryTypes = getInventoryTypes();
        InventoryTypes.add(treasure.getType());
        setInventoryTypes(InventoryTypes);
    }


    public ArrayList<String> getInventoryTypes() {
        return InventoryTypes;
    }


    public void setInventoryTypes(ArrayList<String> inventoryTypes) {
        this.InventoryTypes = inventoryTypes;
    }


    public String getInventoryString() {
        // Doesn't display Trap multiple times if the same adventurer encountered multiple traps?
        ArrayList<String> str_array =  this.InventoryTypes;
        String inv_str = String.join(", ", str_array);
        return inv_str;
    }
}
