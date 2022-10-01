package game;

import java.util.ArrayList;

import celebration.Celebration;
import dungeon.Dungeon;
import dungeon.Room;
import entity.Creature;
import entity.Character;
import treasure.Treasure;

// This Tracker is instantiated at beginning of game and active till end.
// It subscribes for the following published events and maintain a data structure in memory for game status:
//  - adventurer and creature enter a room
//  - adventure/creature wins/loses combat
//  - adventurer celebrates
//  - adventurer/creature defeated/removed
//  - treasure found by adventurer

// Subscribers of the Tracker are Rooms (auto-updates their Character/Creature/Treasure occupancy), the Logger, and the Printer.

// At first we tried to implement the Observer pattern with an Observer interface, but are using references in method calls
// Decision informed from "Game Programming Patterns" a book by Robert Nystrom http://gameprogrammingpatterns.com/observer.html
// "A more modern approach is for an “observer” to be only a reference to a method or function. 
// In languages with first-class functions, and especially ones with closures, this is a much more common way to do observers.""
// 
public class Tracker {

    Dungeon dungeon; // Game Dungeon
    ArrayList<Character> characterList; // An ArrayList of all active Characters
    ArrayList<Creature> creatureList; // An ArrayList of all active Creatures
    ArrayList<Treasure> treasureList; // An ArrayList of all hidden Treasures

    int roundCounter; // Integer Round value
    int treasureCount; // Integer found Treasure count


    /**
     * @param dungeon: Dungeon
     * @param characterList: ArrayList<Character>
     * @param creatureList: ArrayList<Creature>
     * @param treasureList: ArrayList<Treasure>
     * 
     * Constructor for the Tracker.
     */
    public Tracker(Dungeon dungeon, ArrayList<Character> characterList, ArrayList<Creature> creatureList, ArrayList<Treasure> treasureList) {
        this.dungeon = dungeon; // Game Dungeon
        this.characterList = characterList; // An ArrayList of all active Characters
        this.creatureList = creatureList; // An ArrayList of all active Creatures
        this.treasureList = treasureList; // An ArrayList of all hidden Treasures

        this.roundCounter = 0; // Integer Round count value
        this.treasureCount = 0; // Integer found Treasure count
    }


    /**
     * @return int
     * 
     * This returns the Integer Round count value.
     */
    public int getRoundCounter() {
        return roundCounter;
    }


    /**
     * @param roundCounter: int
     * 
     * This sets the Integer Round count value.
     */
    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }


    /**
     * @return int
     * 
     * Returns the Integer Treasure count value.
     */
    public int getTreasureCount() {
        return treasureCount;
    }


    /**
     * @param character: Character
     * @param room: Room
     * 
     * Publishes the Character exiting a Room to the Room's occupancy.
     */
    public void publishCharacterExitsRoom(Character character, Room room){
        ArrayList<Character> charactersInRoom = room.getCharactersInRoom();
        charactersInRoom.remove(character);
        room.setCharactersInRoom(charactersInRoom);
    }


    /**
     * @param character: Character
     * @param room: Room
     * 
     * Publishes a Character entering a Room to the Room's occupancy.
     */
    public void publishCharacterEntersRoom(Character character, Room room){
        ArrayList<Character> charactersInRoom = room.getCharactersInRoom();
        charactersInRoom.add(character);
        room.setCharactersInRoom(charactersInRoom);
    }


    /**
     * @param creature: Creature
     * @param room: Room
     * 
     * Publishes a Creature leaving a Room to the Room's occupancy.
     * Done separately from Characters because it is conventient for the Room's to store Characters and Creatures separately.
     */
    public void publishCreatureExitsRoom(Creature creature, Room room){
        ArrayList<Creature> creaturesInRoom = room.getCreaturesInRoom();
        creaturesInRoom.remove(creature);
        room.setCreaturesInRoom(creaturesInRoom);
    }


    /**
     * @param creature: Creature
     * @param room: Room
     * 
     * Publishes a Creature Entering a Room to the Room's occupancy.
     */
    public void publishCreatureEntersRoom(Creature creature, Room room){
        ArrayList<Creature> creaturesInRoom = room.getCreaturesInRoom();
        creaturesInRoom.add(creature);
        room.setCreaturesInRoom(creaturesInRoom);
    }


    /**
     * @param treasure: Treasure
     * @param room: Room
     * 
     * Publishes Treasure being removed from a Room to the Room's occupancy.
     */
    public void publishTreasureExitsRoom(Treasure treasure, Room room){
        ArrayList<Treasure> treasuresInRoom = room.getTreasuresInRoom();
        treasuresInRoom.remove(treasure);
        room.setTreasuresInRoom(treasuresInRoom);
    }


    /**
     * @param treasure: Treasure
     * @param room: Room
     * 
     * Publishes Treasure being hidden in a Room to the Room's occupancy.
     */
    public void publishTreasureInRoom(Treasure treasure, Room room){
        ArrayList<Treasure> treasuresInRoom = room.getTreasuresInRoom();
        treasuresInRoom.add(treasure);
        room.setTreasuresInRoom(treasuresInRoom);
    }


    /**
     * @param characterList ArrayList<Character> 
     * 
     * Sets the initial Character stats for the Tracker and publishes starting Room occupancy.
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
     * @param creatureList ArrayList<Creature> 
     * 
     * Sets the initial Creature stats for the Tracker and publishes starting Room occupancy.
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
     * @param treasureList ArrayList<Treasure> 
     * 
     * Sets the initial Treasure stats for the Tracker and publishes starting Room occupancy.
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
     * @param room: Room
     * 
     * Published event that Treasure has been found.
     * 
     * Tracker updates the Treasure count and the Room occupancy.
     */
    public void treasureFound(Treasure treasure) {
        this.treasureCount = treasureCount + 1; // Increase counter by one
        this.treasureList.remove(treasure); // remove from treasure list
        
        // publish to room that treasure no longer there
        Room room  = treasure.getLocation();
        publishTreasureExitsRoom(treasure, room);
    }


    /**
     * @return ArrayList<Character>
     * 
     * Returns the active Character stats.
     */
    public ArrayList<Character> getCharacterList() {
        return characterList;
    }


    /**
     * @return ArrayList<Creature>
     * 
     * Returns the active Creature stats.
     */
    public ArrayList<Creature> getCreatureList() {
        return creatureList;
    }


    /**
     * @param character: Character
     * @param creature: Creature
     * 
     * Pubished event that a Character won the fight.
     * 
     * Tracker reduces Creature's health points by 1.
     */
    public void characterWon(Character character, Creature creature) {
        creature.loseHealth(1);
    }


    /**
     * @param character: Character
     * @param creature: Creature
     * 
     * Published event that a Creature won the fight.
     * 
     * Tracker reduces Character's health points by 1.
     */
    public void creatureWon(Character character, Creature creature) {
        character.loseHealth(1);
    }


    /**
     * @param character: Character
     * @param celebration: Celebration
     * 
     * Published even that a Character celebrated.
     * 
     * Tracker does nothing with this as of yet, but it was requested by the assignment.
     */
    public void characterCelebrated(Character character, Celebration celebration) {
            // Requested, but not sure what to do with this information
    }


    /**
     * @param character: Character
     * 
     * Published even that a Character has lost all of its health.
     * 
     * Tracker removes the Character from the active Character list and lets Room subscriper know of new occupancy.
     */
    public void removeCharacter(Character character) {
        this.characterList.remove(character);

        // Publish Occupancy to Rooms
        Room room = character.getLocation();
        publishCharacterExitsRoom(character, room);
    }


    /**
     * @param creature: Creature
     * 
     * Published event that a Creature has lost all of its health.
     * 
     * Tracker removes the Creature from the active Creature list and lets Room subscriber know of new occupancy.
     */
    public void removeCreature(Creature creature) {
        this.creatureList.remove(creature);

        // Publish Creature Occupancy to Rooms
        Room room = creature.getLocation();
        publishCreatureExitsRoom(creature, room);
    }


    /**
     * @param character: Character
     * @param oldRoom: Room
     * @param newNoom: Room
     * 
     * Published even that a Character has moved.
     * 
     * Tracker lets Room subscribers know of new occupancy.
     */
    public void characterMoved(Character character, Room oldRoom, Room newRoom) {
        // Remove character from old room occupancy
        publishCharacterExitsRoom(character, oldRoom);

        // Add character to new room occupancy
        publishCharacterEntersRoom(character, newRoom);
    }


    /**
     * @param creature: Creature
     * @param oldRoom: Room
     * @param newRoom: Room
     * 
     * Published even that a Creature has moved.
     * 
     * Tracker lets Room subscribers know of new occupancy.
     */
    public void creatureMoved(Creature creature, Room oldRoom, Room newRoom) {
        // Remove creature from old room occupancy
        publishCreatureExitsRoom(creature, oldRoom);

        // Add creature to new room occupancy
        publishCreatureEntersRoom(creature, newRoom);
    }
}
