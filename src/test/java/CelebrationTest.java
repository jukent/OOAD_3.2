
import java.beans.Transient;

import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;
import treasure.*;
import fight.*;
import Celebration.*;
import game.Tracker;
import game.GameEngine;


public class CelebrationTest {
    Dungeon dungeon = new Dungeon();
    Character A = new Runner(1,dungeon);
    FightBehavior A.fightBehavior = fightBehavior;

    @Test
    public void testWrap(){
        Celebration tJ = new Jump(FightBehavior);
        tJ = new Spin(tJ);
        
    }
    @Test
    public void testDance(){
        Celebration tJ = new Dance(FightBehavior);
        
    }


}
