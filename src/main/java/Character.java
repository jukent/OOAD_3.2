package main.java;
import java.util.ArrayList;

public abstract class Character extends Entity {

    public TreasureHunt HuntBehavior;
    public ArrayList<Treasure> Inventory = new ArrayList<Treasure>();
    public ArrayList<String> InventoryTypes = new ArrayList<String>();

    protected int MoveCount = 1;


    public void checkPortalInInventory() {
        if (this.InventoryTypes.contains("Portal")) {
            this.MovementBehavior = new BlinkMovement();
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
    }


    public String getInventoryString() {
        // Doesn't display Trap multiple times if the same adventurer encountered multiple traps?
        ArrayList<String> str_array =  this.InventoryTypes;
        String inv_str = String.join(", ", str_array);
        return inv_str;
    }
}
