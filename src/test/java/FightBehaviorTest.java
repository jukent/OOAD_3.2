
import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;


public class FightBehaviorTest {
    Dungeon dungeon = new Dungeon();
    Character runner = new Runner(1, dungeon);
    Character brawler = new Brawler(1,dungeon);
    Character sneaker = new Sneaker(1, dungeon);
    Character thief = new Thief(1,dungeon);


    @Test
    public void testRunnerFightType() {
        Assert.assertEquals("Untrained", runner.getFightType());

    }


    @Test
    public void testBrawlerFightType() {
        Assert.assertEquals("Expert", brawler.getFightType());
    }


    @Test
    public void testSneakerFightType() {
        Assert.assertEquals("Stealth", sneaker.getFightType());
    }


    @Test
    public void testThiefFightType() {
        Assert.assertEquals("Trained", thief.getFightType());
    }
}
