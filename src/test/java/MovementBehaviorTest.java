package test.java;

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

public class MovementBehaviorTest {

    Dungeon dungeon = new Dungeon();

    
    @Test
    public void testStayWithCharacter() {

        ArrayList<Character> characterList = new ArrayList<Character>();
        Brawler character = new Brawler(0, dungeon);
        characterList.add(character);

        Room characterRoom = dungeon.getRoom("(1-0-0)");
        character.setLocation(characterRoom);
        
        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Seeker seeker = new Seeker(1, dungeon);
        creatureList.add(seeker);

        Room oldRoom = dungeon.getRoom("(1-0-0)");
        seeker.setLocation(oldRoom);

        Assert.assertEquals(test_seeker.getLocation(), oldRoom);
        Assert.assertEquals(oldRoom, characterRoom);

        Tracker tracker = new Tracker(dungeon, characterList, creatureList, null);
        tracker.setCharacterStats(characterList);
        tracker.setCreatureStats(creatureList);

        test_seeker.move();
        Room newRoom = seeker.getLocation();
        tracker.creatureMoved(seeker, oldRoom, newRoom);

        Assert.assertEquals(oldRoom, newRoom);
        Assert.assertEquals(characterRoom, newRoom);
    }
}
