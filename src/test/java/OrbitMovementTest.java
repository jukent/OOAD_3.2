package test.java;

import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

import main.java.Orbiter;
import main.java.Creature;
import main.java.Dungeon;
import main.java.Room;
import main.java.Tracker;

public class OrbitMovementTest {

    Dungeon dungeon = new Dungeon();


    @Test
    public void testOrbitClockwise() {

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Orbiter test_creature = new Orbiter(0, dungeon);
        test_creature.setDirection("clockwise");
        creatureList.add(test_creature);

        Assert.assertEquals("clockwise", test_creature.getDirection());

        Room old_room = dungeon.getRoom("(1-0-0)");
        test_creature.setLocation(old_room);

        Assert.assertEquals(test_creature.getLocation(), old_room);

        Tracker tracker = new Tracker(dungeon, null, creatureList, null);
        tracker.setCreatureStats(creatureList);

        test_creature.move();
        Room new_room = test_creature.getLocation();
        tracker.creatureMoved(test_creature, old_room, new_room);

        Assert.assertNotEquals(old_room, new_room);
        Assert.assertEquals("(1-0-1)", new_room.getName());
    }

    @Test
    public void testOrbitCounterClockwise() {

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Orbiter test_creature = new Orbiter(0, dungeon);
        test_creature.setDirection("counterclockwise");
        creatureList.add(test_creature);

        Assert.assertEquals("counterclockwise", test_creature.getDirection());

        Room old_room = dungeon.getRoom("(1-0-0)");
        test_creature.setLocation(old_room);

        Assert.assertEquals(test_creature.getLocation(), old_room);

        Tracker tracker = new Tracker(dungeon, null, creatureList, null);
        tracker.setCreatureStats(creatureList);

        test_creature.move();
        Room new_room = test_creature.getLocation();
        tracker.creatureMoved(test_creature, old_room, new_room);

        Assert.assertNotEquals(old_room, new_room);
        Assert.assertEquals("(1-1-0)", new_room.getName());
    }
}
