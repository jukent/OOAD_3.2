import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import entity.Character;
import entity.*;
import treasure.*;
import game.Tracker;
import game.GameEngine;

public class GameEngineTest {

    GameEngine gameEngine = new GameEngine("ShowNone");
    Boolean endCondition = true;

    Dungeon dungeon = new Dungeon();
    ArrayList<Character> characterList = new ArrayList<Character>();
    ArrayList<Creature> creatureList = new ArrayList<Creature>();
    ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    Tracker tracker = new Tracker(dungeon, characterList, creatureList, treasureList);


    @Test
    public void testPopulateEntities() {
        gameEngine.populateEntities(dungeon, tracker);

        Assert.assertEquals(4, tracker.getCharacterList().size());
        Assert.assertEquals(12, tracker.getCreatureList().size());
        Assert.assertEquals(24, tracker.getTreasureList().size());
    }


    @Test
    public void testEndConditionAllTreasureFound() {
        Assert.assertTrue(endCondition);
        tracker.setTreasureCount(24);

        if (tracker.getTreasureCount() == 24) { 
            endCondition = false;
        } else if (tracker.getCreatureList().size() == 0) { 
            endCondition = true;
        } else if (tracker.getCharacterList().size() == 0) { 
            endCondition = true;
        }
        Assert.assertFalse(endCondition);
    }


    @Test
    public void testEndConditionAllCreaturesDefeated() {
        Assert.assertTrue(endCondition);
        gameEngine.populateEntities(dungeon, tracker);

        tracker.getCreatureList().removeAll(tracker.getCreatureList());

        if (tracker.getTreasureCount() == 24) { 
            endCondition = true;
        } else if (tracker.getCreatureList().size() == 0) { 
            endCondition = false;
        } else if (tracker.getCharacterList().size() == 0) { 
            endCondition = true;
        }
        Assert.assertFalse(endCondition);
    }


    @Test
    public void testEndConditionAllCharactersDefeated() {
        Assert.assertTrue(endCondition);
        gameEngine.populateEntities(dungeon, tracker);

        tracker.getCharacterList().removeAll(tracker.getCharacterList());

        if (tracker.getTreasureCount() == 24) { 
            endCondition = true;
        } else if (tracker.getCreatureList().size() == 0) { 
            endCondition = true;
        } else if (tracker.getCharacterList().size() == 0) { 
            endCondition = false;
        }
        Assert.assertFalse(endCondition);
    }
}
