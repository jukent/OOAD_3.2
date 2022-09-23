public class Potion extends Treasure {
    
    
    /**
     * @param ID
     * @param dungeon
     */
    public Potion(int ID, Dungeon dungeon) {
        this.HPBoost = 1;
        setLocation(dungeon);
    }
}
