package test.java;

import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

import main.java.Brawler;
import main.java.Character;
import main.java.Dungeon;
import main.java.Room;
import main.java.Tracker;

public class RandomWalkMovementTest {

    Dungeon dungeon = new Dungeon();


    @Test
    public void testRandomWalk() {

        ArrayList<Character> characterList = new ArrayList<Character>();
        Brawler test_character = new Brawler(0, dungeon);
        characterList.add(test_character);

        Room old_room = dungeon.getRoom("(1-0-0)");
        test_character.setLocation(old_room);

        Assert.assertEquals(test_character.getLocation(), old_room);

        Tracker tracker = new Tracker(dungeon, null, null, null);
        tracker.setCharacterStats(characterList);

        test_character.move();
        Room new_room = test_character.getLocation();
        tracker.characterMoved(test_character, old_room, new_room);

        Assert.assertNotEquals(old_room, new_room);


        // check there is exit from old_room to new_room
        ArrayList<String> exits = old_room.getExits();
        Assert.assertTrue(exits.contains(new_room.getName()));
    }
}