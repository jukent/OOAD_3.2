package test.java;

import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

import main.java.Brawler;
import main.java.Creature;
import main.java.Character;
import main.java.Dungeon;
import main.java.Portal;
import main.java.Room;
import main.java.Blinker;
import main.java.Tracker;
import main.java.Treasure;

public class BlinkMovementTest {

    Dungeon dungeon = new Dungeon();

    @Test
    public void testCreatureBlink() {

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Blinker test_creature = new Blinker(0, dungeon);
        creatureList.add(test_creature);

        Room old_room = test_creature.getLocation();
        int level = old_room.getLevel();
        Assert.assertEquals(4, level);

        Tracker tracker = new Tracker(dungeon, null, creatureList, null);
        tracker.setCreatureStats(creatureList);

        Assert.assertEquals("Blink", test_creature.getMovementType());
        
        test_creature.move();
        Room new_room = test_creature.getLocation();
        tracker.creatureMoved(test_creature, old_room, new_room);

        Assert.assertNotEquals(old_room, new_room);
    }

    @Test
    public void testCharacterBlink() {

        ArrayList<Character> characterList = new ArrayList<Character>();
        Brawler test_character = new Brawler(0, dungeon);
        characterList.add(test_character);

        Room old_room = test_character.getLocation();

        ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
        Portal portal = new Portal(1, dungeon);
        treasureList.add(portal);

        Tracker tracker = new Tracker(dungeon, characterList, null, null);
        tracker.setCharacterStats(characterList);
        tracker.setTreasureStats(treasureList);

        test_character.setInventory(portal);
        tracker.treasureFound(portal);

        Assert.assertEquals("Blink", test_character.getMovementType());

        test_character.move();
        Room new_room = test_character.getLocation();
        tracker.characterMoved(test_character, old_room, new_room);

        Assert.assertNotEquals(old_room, new_room);
    }
}
