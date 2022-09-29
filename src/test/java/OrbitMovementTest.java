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
        Orbiter test_orbiter = new Orbiter(0, dungeon);
        test_orbiter.setDirection("clockwise");
        creatureList.add(test_orbiter);

        Assert.assertEquals("clockwise", test_orbiter.getDirection());

        Room old_room = dungeon.getRoom("(1-0-0)");
        test_orbiter.setLocation(old_room);

        Assert.assertEquals(test_orbiter.getLocation(), old_room);

        Tracker tracker = new Tracker(dungeon, null, creatureList, null);
        tracker.setCreatureStats(creatureList);

        Assert.assertEquals("Orbit", test_orbiter.getMovementType());

        test_orbiter.move();
        Room new_room = test_orbiter.getLocation();
        tracker.creatureMoved(test_orbiter, old_room, new_room);

        Assert.assertNotEquals(old_room, new_room);
        Assert.assertEquals("(1-0-1)", new_room.getName());
    }

    @Test
    public void testOrbitCounterClockwise() {

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Orbiter test_orbiter = new Orbiter(0, dungeon);
        test_orbiter.setDirection("counterclockwise");
        creatureList.add(test_orbiter);

        Assert.assertEquals("counterclockwise", test_orbiter.getDirection());

        Room old_room = dungeon.getRoom("(1-0-0)");
        test_orbiter.setLocation(old_room);

        Assert.assertEquals(test_orbiter.getLocation(), old_room);

        Tracker tracker = new Tracker(dungeon, null, creatureList, null);
        tracker.setCreatureStats(creatureList);

        Assert.assertEquals("Orbit", test_orbiter.getMovementType());

        test_orbiter.move();
        Room new_room = test_orbiter.getLocation();
        tracker.creatureMoved(test_orbiter, old_room, new_room);

        Assert.assertNotEquals(old_room, new_room);
        Assert.assertEquals("(1-1-0)", new_room.getName());
    }
}
