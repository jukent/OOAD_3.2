
import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;
import fight.*;
import celebration.*;


public class CelebrationTest {
    Dungeon dungeon = new Dungeon();
    Character A = new Runner(1, dungeon);
    FightBehavior fightBehavior = A.getFightBehavior();


    @Test
    public void testWrap(){
        Celebration tJ = new JumpCelebration(fightBehavior);
        tJ = new SpinCelebration(tJ);
        Assert.assertEquals("Untrained",tJ.fightRef.fightType);

    }
}
