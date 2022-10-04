
import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;


public class FightBehaviorTest {
    Dungeon dungeon = new Dungeon();
    Character A = new Runner(1, dungeon);
    Character B = new Brawler(1,dungeon);
    Character C = new Sneaker(1, dungeon);
    Character D = new Thief(1,dungeon);



    @Test
    public void testRunnerFightType(){
        Assert.assertEquals("Untrained",A.getFightType());

    }

    @Test
    public void testBrawlerFightType(){
        Assert.assertEquals("Expert",B.getFightType());

    }

    @Test
    public void testSneakerFightType(){
        Assert.assertEquals("Stealth",C.getFightType());

    }

    @Test
    public void testThiefFightType(){
        Assert.assertEquals("Trained",D.getFightType());

    }

}