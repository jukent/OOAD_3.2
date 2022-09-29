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
import main.java.BlinkMovement;
import main.java.Blinker;
import main.java.Treasure;

public class BlinkMovementTest {

    Dungeon dungeon = new Dungeon();

    @Test
    public void testCreatureBlink() {

        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        Blinker blinker = new Blinker(0, dungeon);
        creatureList.add(blinker);

        Room old_room = blinker.getLocation();
        int level = old_room.getLevel();
        Assert.assertEquals(4, level);

        Assert.assertEquals("Blink", blinker.getMovementType());

        blinker.move();
        Room new_room = blinker.getLocation();

        Assert.assertNotEquals(old_room, new_room);
    }


    @Test
    public void testCharacterBlink() {

        ArrayList<Character> characterList = new ArrayList<Character>();
        Brawler character = new Brawler(0, dungeon);
        characterList.add(character);

        Room old_room = character.getLocation();

       Portal portal = new Portal(1, dungeon);
       Assert.assertEquals("Portal", portal.getType());

        character.setInventory(portal);
        Assert.assertTrue(character.Inventory.contains(portal));

        ArrayList<String> inventory_array = new ArrayList<String>();
        inventory_array.add(portal.getType());
        Assert.assertEquals(character.InventoryTypes, inventory_array);
        Assert.assertTrue(character.InventoryTypes.contains(portal.getType()));
        Assert.assertTrue(character.InventoryTypes.contains("Portal"));

        character.checkPortalInInventory();
        Assert.assertEquals("Blink", character.getMovementType());

        character.move();
        Room new_room = character.getLocation();

        Assert.assertNotEquals(old_room, new_room);
    }
}
