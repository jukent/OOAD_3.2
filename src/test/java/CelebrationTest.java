
import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;
import fight.*;
import celebration.*;
import game.Tracker;
import game.GameEngine;


public class CelebrationTest {
    Dungeon dungeon = new Dungeon();
    Character A = new Runner(1, dungeon);
    FightBehavior fightBehavior = A.getFightBehavior();


    @Test
    public void testWrap(){
        Celebration tJ = new Jump(FightBehavior);
        tJ = new Spin(tJ);
        Assert.assertEquals("Untrained",tJ.fightBehavior.fightType)

    }
}
