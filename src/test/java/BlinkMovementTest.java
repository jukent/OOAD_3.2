import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Blinker;
import entity.Brawler;
import treasure.Portal;

public class BlinkMovementTest {

    Dungeon dungeon = new Dungeon();

    @Test
    public void testCreatureBlink() {
        Blinker blinker = new Blinker(0, dungeon);

        Room oldRoom = blinker.getLocation();
        int level = oldRoom.getLevel();
        Assert.assertEquals(4, level);

        Assert.assertEquals("Blink", blinker.getMovementType());

        blinker.move();
        Room newRoom = blinker.getLocation();

        Assert.assertNotEquals(oldRoom, newRoom);
    }


    @Test
    public void testCharacterBlink() {
        Brawler character = new Brawler(0, dungeon);

        Room oldRoom = character.getLocation();

        Portal portal = new Portal(1, dungeon);
        Assert.assertEquals("Portal", portal.getType());

        character.addInventory(portal);
        Assert.assertTrue(character.getInventory().contains(portal));

        ArrayList<String> inventoryArray = new ArrayList<String>();
        inventoryArray.add(portal.getType());
        Assert.assertEquals(character.getInventoryTypes(), inventoryArray);
        Assert.assertTrue(character.getInventoryTypes().contains(portal.getType()));
        Assert.assertTrue(character.getInventoryTypes().contains("Portal"));

        character.checkPortalInInventory();
        Assert.assertEquals("Blink", character.getMovementType());

        character.move();
        Room newRoom = character.getLocation();
        Assert.assertNotEquals(oldRoom, newRoom);
    }
}