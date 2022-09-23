public class Gem extends Treasure {


    /**
     * @param ID
     */
    public Gem(int ID, Dungeon dungeon) {
        this.AdversaryFightBonus = 1;
        setLocation(dungeon);
    }
}
