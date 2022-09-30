package movement.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.Room;
import entity.character.Brawler;
import entity.character.Character;
import entity.creature.Blinker;
import entity.creature.Creature;
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
        Assert.assertTrue(character.Inventory.contains(portal));

        ArrayList<String> inventoryArray = new ArrayList<String>();
        inventoryArray.add(portal.getType());
        Assert.assertEquals(character.InventoryTypes, inventoryArray);
        Assert.assertTrue(character.InventoryTypes.contains(portal.getType()));
        Assert.assertTrue(character.InventoryTypes.contains("Portal"));

        character.checkPortalInInventory();
        Assert.assertEquals("Blink", character.getMovementType());

        character.move();
        Room newRoom = character.getLocation();
        Assert.assertNotEquals(oldRoom, newRoom);
    }
}
