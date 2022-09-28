import java.util.ArrayList;

public abstract class Character extends Entity {

    public TreasureHunt HuntBehavior;
    public ArrayList<Treasure> Inventory = new ArrayList<Treasure>();
    public ArrayList<String> InventoryTypes = new ArrayList<String>();

    protected int MoveCount = 1;


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
     * @return int
     * 
     * This method returns how much treasure a Character has found.
     */
    public int getTreasureCount() {
        return this.Inventory.size();
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
        ArrayList<String> str_array =  this.InventoryTypes;
        String inv_str = String.join(", ", str_array);
        return inv_str;
    }
}
