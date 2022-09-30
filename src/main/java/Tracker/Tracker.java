package Tracker;

import java.util.ArrayList;

import Celebration.Celebration;
import Dungeon.Dungeon;
import Dungeon.Room;
import Entity.Character.Character;
import Entity.Creature.Creature;
import Treasure.Treasure;

public class Tracker {
    // instantiated at beginning of game and active till end
    // will subscribe for the published events and maintain a data structure in memory for game status

    // publish adventurer and creature enter a room
    // publish adventure/creature wins/loses combat
    // publish adventurer celebrates
    // publish adventurer/creature defeated/removed
    // publish treasure found by adventurer

    // A more modern approach is for an “observer” to be only a reference to a method or function. 
    // In languages with first-class functions, and especially ones with closures, this is a much more common way to do observers.
    // http://gameprogrammingpatterns.com/observer.html

    Dungeon dungeon;
    ArrayList<Character> characterList;
    ArrayList<Character> deadCharacterList;

    ArrayList<Creature> creatureList;
    ArrayList<Creature> deadCreatureList;
    
    ArrayList<Treasure> treasureList;
    ArrayList<Treasure> foundTreasureList;

    int roundCounter;
    int treasureCount;


    /**
     * @param dungeon
     * @param characterList
     * @param creatureList
     * @param treasureList
     */
    public Tracker(Dungeon dungeon, ArrayList<Character> characterList, ArrayList<Creature> creatureList, ArrayList<Treasure> treasureList) {
        this.dungeon = dungeon;
        this.characterList = characterList;
        this.creatureList = creatureList;
        this.treasureList = treasureList;

        this.roundCounter = 0;
        this.treasureCount = 0;
    }


    /**
     * @return
     */
    public int getRoundCounter() {
        return roundCounter;
    }


    /**
     * @param roundCounter
     */
    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }


    /**
     * @return
     */
    public int getTreasureCount() {
        return treasureCount;
    }


    /**
     * @param character
     * @param room
     */
    public void publishCharacterExitsRoom(Character character, Room room){
        ArrayList<Character> characters_in_room = room.getCharactersInRoom();
        characters_in_room.remove(character);
        room.setCharactersInRoom(characters_in_room);
    }


    /**
     * @param character
     * @param room
     */
    public void publishCharacterEntersRoom(Character character, Room room){
        ArrayList<Character> characters_in_room = room.getCharactersInRoom();
        characters_in_room.add(character);
        room.setCharactersInRoom(characters_in_room);
    }


    /**
     * @param creature
     * @param room
     */
    public void publishCreatureExitsRoom(Creature creature, Room room){
        ArrayList<Creature> creatures_in_room = room.getCreaturesInRoom();
        creatures_in_room.remove(creature);
        room.setCreaturesInRoom(creatures_in_room);
    }


    /**
     * @param creature
     * @param room
     */
    public void publishCreatureEntersRoom(Creature creature, Room room){
        ArrayList<Creature> creatures_in_room = room.getCreaturesInRoom();
        creatures_in_room.add(creature);
        room.setCreaturesInRoom(creatures_in_room);
    }


    /**
     * @param treasure
     * @param room
     */
    public void publishTreasureExitsRoom(Treasure treasure, Room room){
        ArrayList<Treasure> treasures_in_room = room.getTreasuresInRoom();
        treasures_in_room.remove(treasure);
        room.setTreasuresInRoom(treasures_in_room);
    }


    /**
     * @param treasure
     * @param room
     */
    public void publishTreasureInRoom(Treasure treasure, Room room){
        ArrayList<Treasure> treasures_in_room = room.getTreasuresInRoom();
        treasures_in_room.add(treasure);
        room.setTreasuresInRoom(treasures_in_room);
    }


    /**
     * @param characterList
     */
    public void setCharacterStats(ArrayList<Character> characterList) {
        this.characterList = characterList;
        // Publish Character occupancy to rooms
        for (Character c: characterList) {
            Room room = c.getLocation();
            publishCharacterEntersRoom(c, room);
        }
    }


    /**
     * @param creatureList
     */
    public void setCreatureStats(ArrayList<Creature> creatureList) {
        this.creatureList = creatureList;
        // Publish Creature occupancy to rooms
        for (Creature c: creatureList) {
            Room room = c.getLocation();
            publishCreatureEntersRoom(c, room);
        }
    }


    /**
     * @param treasureList
     */
    public void setTreasureStats(ArrayList<Treasure> treasureList) {
        this.treasureList = treasureList;
        // Publish Treasure occupancy to rooms
        for (Treasure t: treasureList) {
            Room room = t.getLocation();
            publishTreasureInRoom(t, room);
        }
    }


    /**
     * @param room
     * @return
     * 
     */
    public void treasureFound(Treasure treasure) {
        this.treasureCount = treasureCount + 1; // Increase counter by one
        this.treasureList.remove(treasure); // remove from treasure list
        
        // publish to room that treasure no longer there
        Room room  = treasure.getLocation();
        publishTreasureExitsRoom(treasure, room);
    }


    /**
     * @return
     */
    public ArrayList<Character> getCharacterList() {
        return characterList;
    }


    /**
     * @return
     */
    public ArrayList<Creature> getCreatureList() {
        return creatureList;
    }


    /**
     * @param character
     * @param creature
     */
    public void characterWon(Character character, Creature creature) {
        creature.loseHealth(1);
    }


    /**
     * @param character
     * @param creature
     */
    public void creatureWon(Character character, Creature creature) {
        character.loseHealth(1);
    }


    /**
     * @param character
     * @param celebration
     */
    public void characterCelebrated(Character character, Celebration celebration) {
            // Requested, but not sure what to do with this information
    }


    /**
     * @param character
     */
    public void removeCharacter(Character character) {
        this.characterList.remove(character);

        // Publish Occupancy to Rooms
        Room room = character.getLocation();
        publishCharacterExitsRoom(character, room);
    }


    /**
     * @param creature
     */
    public void removeCreature(Creature creature) {
        this.creatureList.remove(creature);

        // Publish Creature Occupancy to Rooms
        Room room = creature.getLocation();
        publishCreatureExitsRoom(creature, room);
    }


    /**
     * @param character
     * @param old_room
     * @param new_room
     */
    public void characterMoved(Character character, Room old_room, Room new_room) {
        // Remove character from old room occupancy
        publishCharacterExitsRoom(character, old_room);

        // Add character to new room occupancy
        publishCharacterEntersRoom(character, new_room);
    }


    /**
     * @param creature
     * @param old_room
     * @param new_room
     */
    public void creatureMoved(Creature creature, Room old_room, Room new_room) {
        // Remove creature from old room occupancy
        publishCreatureExitsRoom(creature, old_room);

        // Add creature to new room occupancy
        publishCreatureEntersRoom(creature, new_room);
    }
}
