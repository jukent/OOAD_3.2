package movement.tests;

import org.junit.Test;
import java.util.ArrayList;
import org.junit.Assert;

import dungeon.Dungeon;
import dungeon.Room;
import entity.creature.Creature;
import entity.creature.Orbiter;

public class OrbitMovementTest {

    Dungeon dungeon = new Dungeon();
    

    @Test
    public void testOrbitClockwise() {
        Orbiter test_orbiter = new Orbiter(0, dungeon);
        orbiter.setDirection("clockwise");

        Assert.assertEquals("clockwise", orbiter.getDirection());

        Room oldRoom = dungeon.getRoom("(1-0-0)");
        orbiter.setLocation(oldRoom);

        Assert.assertEquals(orbiter.getLocation(), oldRoom);

        Assert.assertEquals("Orbit", orbiter.getMovementType());

        orbiter.move();
        Room newRoom = orbiter.getLocation();

        Assert.assertNotEquals(oldRoom, newRoom);
        Assert.assertEquals("(1-0-1)", newRoom.getName());
    }


    @Test
    public void testOrbitCounterClockwise() {
        Orbiter orbiter = new Orbiter(0, dungeon);
        orbiter.setDirection("counterclockwise");

        Assert.assertEquals("counterclockwise", test_orbiter.getDirection());

        Room oldRoom = dungeon.getRoom("(1-0-0)");
        orbiter.setLocation(oldRoom);

        Assert.assertEquals(orbiter.getLocation(), oldRoom);

        Assert.assertEquals("Orbit", orbiter.getMovementType());

        orbiter.move();
        Room newRoom = orbiter.getLocation();

        Assert.assertNotEquals(oldRoom, newRoom);
        Assert.assertEquals("(1-1-0)", newRoom.getName());
    }
}
