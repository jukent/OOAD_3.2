package Entity.Character;

import Dungeon.Dungeon;
import FightingBehavior.ExpertFighter;
import TreasureHuntBehavior.CarelessHunt;

public class Brawler extends Character { 
    // Example of inheritance
    
    
    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Brawler constructor must be passed in an integer ID 'A' and the Dungeon.
     * Brawler is constructed with starting room.
     */
    public Brawler(int A, Dungeon map) {
        super.ID = A;
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.FightBehavior = new ExpertFighter();
        this.HuntBehavior = new CarelessHunt();
        name = "Brawler";
    }
}
