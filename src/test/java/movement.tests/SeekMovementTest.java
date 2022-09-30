package movement.tests;

import org.junit.Test;
import java.util.ArrayList;
import org.junit.Assert;

import dungeon.Dungeon;
import dungeon.Room;
import entity.character.Brawler;
import entity.character.Character;
import entity.creature.Creature;
import entity.creature.Seeker;
import track.Tracker;

public class SeekMovementTest {

    Dungeon dungeon = new Dungeon();


    @Test
    public void testAwaitCharacter() {

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Seeker test_seeker = new Seeker(1, dungeon);
        creatureList.add(test_seeker);

        Room old_room = dungeon.getRoom("(1-0-0)");
        test_seeker.setLocation(old_room);

        Assert.assertEquals(test_seeker.getLocation(), old_room);

        Tracker tracker = new Tracker(dungeon, null, creatureList, null);
        tracker.setCreatureStats(creatureList);

        Assert.assertEquals("Seek", test_seeker.getMovementType());

        test_seeker.move();
        Room new_room = test_seeker.getLocation();
        tracker.creatureMoved(test_seeker, old_room, new_room);

        Assert.assertEquals(old_room, new_room);
    }


    @Test
    public void testMove2Character() {

        ArrayList<Character> characterList = new ArrayList<Character>();
        Brawler test_character = new Brawler(0, dungeon);
        characterList.add(test_character);

        Room character_room = dungeon.getRoom("(1-0-0)");
        test_character.setLocation(character_room);

        Assert.assertEquals(test_character.getLocation(), character_room);
        
        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Seeker test_seeker = new Seeker(1, dungeon);
        creatureList.add(test_seeker);

        Room old_room = dungeon.getRoom("(1-1-0)");
        test_seeker.setLocation(old_room);

        Assert.assertEquals(test_seeker.getLocation(), old_room);

        Assert.assertNotEquals(old_room, character_room);

        // check there is exit to character_room
        ArrayList<String> exits = old_room.getExits();
        Assert.assertTrue(exits.contains(character_room.getName()));

        Tracker tracker = new Tracker(dungeon, characterList, creatureList, null);
        tracker.setCharacterStats(characterList);
        tracker.setCreatureStats(creatureList);

        // check that we can get characters in room
        ArrayList<Character> characters_in_room = character_room.getCharactersInRoom();
        Assert.assertEquals(characters_in_room, characterList);

        Assert.assertEquals("Seek", test_seeker.getMovementType());

        test_seeker.move();
        Room new_room = test_seeker.getLocation();
        tracker.creatureMoved(test_seeker, old_room, new_room);

        Assert.assertNotEquals(test_seeker.getLocation(), old_room);
        Assert.assertEquals(character_room, new_room);
    } 
}
