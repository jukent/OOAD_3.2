import org.junit.Test;


import dungeon.*;
import entity.*;
import entity.Character;
import treasure.Armor;
import treasure.Trap;
import treasure.Treasure;
import game.Tracker;

import java.util.ArrayList;
import org.junit.Assert;

public class TreasureHuntTest {
    
    Dungeon dungeon = new Dungeon();
    ArrayList<Character> characterList = new ArrayList<Character>();
    ArrayList<Creature> creatureList = new ArrayList<Creature>();
    ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    Tracker tracker = new Tracker(dungeon, characterList, creatureList, treasureList);


    @Test
    public void treasureHuntSuccessTest() {
        Brawler character = new Brawler(0, dungeon);
        Room room = dungeon.getRoom("(1-0-0)");
        character.setLocation(room);
        characterList.add(character);
        tracker.setCharacterStats(characterList);

        Armor treasure = new Armor(1, dungeon);
        treasure.setLocation(room);
        treasureList.add(treasure);
        tracker.setTreasureStats(treasureList);

        int neededScore = 0;
        int score = character.searchTreasure();
        Assert.assertTrue(score > neededScore);

        ArrayList<Treasure> treasureInRoom = character.getLocation().getTreasuresInRoom();
        Assert.assertEquals(1, treasureInRoom.size());
        Treasure currentItem = treasureInRoom.get(0); 
        
        character.addInventory(currentItem);
        tracker.treasureFound(currentItem);

        Assert.assertEquals(1, character.getInventory().size());
    }


    @Test
    public void treasureHuntDuplicateSuccessTest() {
        Brawler character = new Brawler(0, dungeon);
        Room room = dungeon.getRoom("(1-0-0)");
        character.setLocation(room);

        Armor treasure = new Armor(1, dungeon);
        character.addInventory(treasure);

        Armor treasure2 = new Armor(2, dungeon);
        treasure2.setLocation(room);
        treasureList.add(treasure2);

        Assert.assertTrue(character.getInventoryTypes().contains(treasure2.getType()));
    }


    @Test
    public void treasureHuntDuplicateTrap() {
        Brawler character = new Brawler(0, dungeon);
        Room room = dungeon.getRoom("(1-0-0)");
        character.setLocation(room);

        Trap treasure = new Trap(1, dungeon);
        character.addInventory(treasure);

        Trap treasure2 = new Trap(2, dungeon);
        treasure2.setLocation(room);
        treasureList.add(treasure2);

        if (character.getInventoryTypes().contains(treasure2.getType())) {
            if (treasure2.getType() == "Trap") {
                character.addInventory(treasure2);
                tracker.treasureFound(treasure2);
            }
        }
        Assert.assertEquals(2, character.getInventory().size());
        Assert.assertEquals("Trap, Trap", character.getInventoryString());
    }


    @Test
    public void carelessTreasureHuntTest() {
        Brawler brawler = new Brawler(0, dungeon);
        Assert.assertEquals("Careless", brawler.getSearchBehavior().getType());
    }


    @Test
    public void carefulTreasureHuntTest() {
        Thief thief = new Thief(0, dungeon);
        Assert.assertEquals("Careful", thief.getSearchBehavior().getType());
    }

    @Test
    public void quickTreasureHuntTest() {
        Runner runner = new Runner(0, dungeon);
        Assert.assertEquals("Quick", runner.getSearchBehavior().getType());
    }
}
