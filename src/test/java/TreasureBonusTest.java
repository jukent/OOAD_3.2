
import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;
import treasure.*;


public class TreasureBonusTest {
    private Dungeon dungeon = new Dungeon();
    private Character A = new Runner(1, dungeon);
    private Treasure B = new Potion(1,dungeon);
    private Treasure C = new Armor(1,dungeon);
    private Treasure D = new Gem(1,dungeon);
    private Treasure E = new Sword(1,dungeon);
    private Treasure F = new Trap(1,dungeon);


    @Test
    public void testBonuses(){
        Assert.assertEquals(1,B.getHPBoost());
        Assert.assertEquals(-1,C.getAdversaryFightBonus());
        Assert.assertEquals(1,D.getAdversaryFightBonus());
        Assert.assertEquals(1,E.getOwnerFightBonus());
    }


    @Test
    public void testHPAddition(){
        A.addHealth(B.getHPBoost());
        Assert.assertEquals(4,A.getHealth());
    }


    @Test
    public void testTrap(){
        A.loseHealth(F.getTakeDamage());
        Assert.assertEquals(2,A.getHealth());
    }
}