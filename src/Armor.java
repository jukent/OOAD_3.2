public class Armor extends Treasure {
    

    /**
     * @param ID
     */
    public Armor(int ID, Dungeon dungeon){
        this.AdversaryFightBonus = -1;
        setLocation(dungeon);
    }
}