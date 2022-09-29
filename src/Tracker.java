import java.util.ArrayList;

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


    public Tracker(Dungeon dungeon, ArrayList<Character> characterList, ArrayList<Creature> creatureList, ArrayList<Treasure> treasureList) {
        this.dungeon = dungeon;
        this.characterList = characterList;
        this.creatureList = creatureList;
        this.treasureList = treasureList;

        this.roundCounter = 0;
        this.treasureCount = 0;
    }


    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }


    public int getTreasureCount() {
        return treasureCount;
    }


    public void publishCharacterExitsRoom(Character character, Room room){
        ArrayList<Character> characters_in_room = room.getCharactersInRoom();
        characters_in_room.remove(character);
        room.setCharactersInRoom(characters_in_room);
    }

    public void publishCharacterEntersRoom(Character character, Room room){
        ArrayList<Character> characters_in_room = room.getCharactersInRoom();
        characters_in_room.add(character);
        room.setCharactersInRoom(characters_in_room);
    }

    public void publishCreatureExitsRoom(Creature creature, Room room){
        ArrayList<Creature> creatures_in_room = room.getCreaturesInRoom();
        creatures_in_room.remove(creature);
        room.setCreaturesInRoom(creatures_in_room);
    }

    public void publishCreatureEntersRoom(Creature creature, Room room){
        ArrayList<Creature> creatures_in_room = room.getCreaturesInRoom();
        creatures_in_room.add(creature);
        room.setCreaturesInRoom(creatures_in_room);
    }

    public void publishTreasureExitsRoom(Treasure treasure, Room room){
        ArrayList<Treasure> treasures_in_room = room.getTreasuresInRoom();
        treasures_in_room.remove(treasure);
        room.setTreasuresInRoom(treasures_in_room);
    }

    public void publishTreasureInRoom(Treasure treasure, Room room){
        ArrayList<Treasure> treasures_in_room = room.getTreasuresInRoom();
        treasures_in_room.add(treasure);
        room.setTreasuresInRoom(treasures_in_room);
    }

    public void setCharacterStats(ArrayList<Character> characterList) {
        this.characterList = characterList;
        // Publish Character occupancy to rooms
        for (Character c: characterList) {
            Room room = c.getLocation();
            publishCharacterEntersRoom(c, room);
        }
    }

    public void setCreatureStats(ArrayList<Creature> creatureList) {
        this.creatureList = creatureList;
        // Publish Creature occupancy to rooms
        for (Creature c: creatureList) {
            Room room = c.getLocation();
            publishCreatureEntersRoom(c, room);
        }
    }

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

    public ArrayList<Character> getCharacterList() {
        return characterList;
    }

    public ArrayList<Creature> getCreatureList() {
        return creatureList;
    }

    public void characterWon(Character character, Creature creature) {
        creature.loseHealth(1);
    }

    public void creatureWon(Character character, Creature creature) {
        character.loseHealth(1);
    }


    public void characterCelebrated(Character character, Celebration celebration) {
            // Requested, but not sure what to do with this information
    }

    public void removeCharacter(Character character) {
        this.characterList.remove(character);

        // Publish Occupancy to Rooms
        Room room = character.getLocation();
        publishCharacterExitsRoom(character, room);
    }

    public void removeCreature(Creature creature) {
        this.creatureList.remove(creature);

        // Publish Creature Occupancy to Rooms
        Room room = creature.getLocation();
        publishCreatureExitsRoom(creature, room);
    }


    public void characterMoved(Character character, Room old_room, Room new_room) {
        // Remove character from old room occupancy
        publishCharacterExitsRoom(character, old_room);

        // Add character to new room occupancy
        publishCharacterEntersRoom(character, new_room);
    }


    public void creatureMoved(Creature creature, Room old_room, Room new_room) {
        // Remove creature from old room occupancy
        publishCreatureExitsRoom(creature, old_room);

        // Add creature to new room occupancy
        publishCreatureEntersRoom(creature, new_room);
    }
}
