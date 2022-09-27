import java.util.ArrayList;

public class Tracker {
    // instantiated at beginning of game and active till end
    // will subscribe for the published events and maintain a data structure in memory for game status

    // publish adventurer and creature enter a room
    // publish adventure/creature wins/loses combat
    //publish adventurer celebrates
    // publish adventurer/creature defeated/removed
    // publish treasure found by adventurer

    Dungeon dungeon;
    ArrayList<Characters> characterList;
    ArrayList<Creatures> creatureList;
    ArrayList<Treasure> treasureList;
    int roundCounter;
    

    public Tracker(Dungeon dungeon, ArrayList<Characters> characterList, ArrayList<Creatures> creatureList, ArrayList<Treasure> treasureList) {
        this.dungeon = dungeon;
        this.characterList = characterList;
        this.creatureList = creatureList;
        this.treasureList = treasureList;

        this.roundCounter = 0;
    }


    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }


    public void setCharacterStats(ArrayList<Characters> characterList) {
        this.characterList = characterList;
    }

    public void setCreatureStats(ArrayList<Creatures> creatureList) {
        this.creatureList = creatureList;
    }

    public void setTreasureStats(ArrayList<Treasure> treasureList) {
        this.treasureList = treasureList;

    }


    /**
     * @param room
     * @return
     */
    public ArrayList<Treasure> getTreasureInRoom(Room room) {
        ArrayList<Treasure> treasureInRoom = new ArrayList<Treasure>();
        for (Treasure t: this.treasureList) {
            if (t.getLocation() == room) {
                treasureInRoom.add(t);
            }
        }
        return treasureInRoom;
    }


    /**
     * @param room
     * @return
     * 
     * I'm getting confused about making the tracker DO things vs just observe
     * Do we remove treasures when they are foundj from the treasure list, or just from the room?
     */
    public void removeTreasure(Treasure treasure) {
        this.treasureList.remove(treasure);
    }

    
    /**
     * @param room: Room
     * 
     * Method to get Creatures from a particular room.
     */
    public ArrayList<Creatures> getCreaturesInRoom(Room room) {
        ArrayList<Creatures> creatures_in_room = new ArrayList<>();
        for (Creatures c: creatureList) {
            Room creature_location = c.getLocation();
            if (creature_location == room) {
                creatures_in_room.add(c);
            } 
        }
       return creatures_in_room;  
    }


    /**
     * @param room: Room
     * 
     * Method to get Characters from a particular room.
     */
    public ArrayList<Characters> getCharactersInRoom(Room room) {
        ArrayList<Characters> characters_in_room = new ArrayList<>();
        for (Characters c: characterList) {
            Room character_location = c.getLocation();
            if (character_location == room) {
                characters_in_room.add(c);
            } 
        }
        return characters_in_room;  
    }

}