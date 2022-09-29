package main.java;
public class Sword extends Treasure {
    
    
    /**
     * @param ID
     * @param dungeon
     */
    public Sword(int ID, Dungeon dungeon) {
        this.OwnerFightBonus = 1;
        this.TreasureType = "Sword";
        setLocation(dungeon);
    }
}
