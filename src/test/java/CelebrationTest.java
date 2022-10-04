
import java.beans.Transient;

import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;
import treasure.*;
import fight.*;
import celebration.*;
import game.Tracker;
import game.GameEngine;


public class CelebrationTest {
    Dungeon dungeon = new Dungeon();
    Character A = new Runner(1, dungeon);
    FightBehavior fightBehavior = A.getFightBehavior();


    @Test
    public void testWrap() {
        Celebration tJ = new JumpCelebration(fightBehavior);
        tJ = new SpinCelebration(tJ); 
    }


    @Test
    public void testDance() {
        Celebration tJ = new DanceCelebration(fightBehavior);
    }
}
