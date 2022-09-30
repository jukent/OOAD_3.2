import org.junit.Test;
import java.util.ArrayList;
import org.junit.Assert;

import Entity.Character.Character;
import Entity.Character.Brawler;
import Entity.Creature.Creature;
import Entity.Creature.Seeker;
import Dungeon.Dungeon;
import Dungeon.Room;
import Tracker.Tracker;

public class MovementBehaviorTest {

    Dungeon dungeon = new Dungeon();

    @Test
    public void testStayWithCharacter() {

        ArrayList<Character> characterList = new ArrayList<Character>();
        Brawler test_character = new Brawler(0, dungeon);
        characterList.add(test_character);

        Room character_room = dungeon.getRoom("(1-0-0)");
        test_character.setLocation(character_room);
        
        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Seeker test_seeker = new Seeker(1, dungeon);
        creatureList.add(test_seeker);

        Room old_room = dungeon.getRoom("(1-0-0)");
        test_seeker.setLocation(old_room);

        Assert.assertEquals(test_seeker.getLocation(), old_room);
        Assert.assertEquals(old_room, character_room);

        Tracker tracker = new Tracker(dungeon, characterList, creatureList, null);
        tracker.setCharacterStats(characterList);
        tracker.setCreatureStats(creatureList);

        test_seeker.move();
        Room new_room = test_seeker.getLocation();
        tracker.creatureMoved(test_seeker, old_room, new_room);

        Assert.assertEquals(old_room, new_room);
        Assert.assertEquals(character_room, new_room);
    }
}
