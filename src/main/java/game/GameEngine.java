package game;

import java.util.ArrayList;
import java.util.Scanner;

import celebration.*;
import dungeon.*;
import entity.*;
import entity.Character;
import fight.FightBehavior;
import treasure.*;

public class GameEngine {

    private String output = "ShowNone"; // OneScreen,ShowEnding,ShowAll,ShowNone

    protected Dungeon dungeon = new Dungeon(); // Example of identity
    // Dungeon is an example of identity. While we could create an instance
    // of dungeon in each character, by having the same instance of dungeon
    // passed to the characters, we can assure that each character
    // has a reference to the same map. This eliminates any issues that may come
    // from specific map generations. We pass its identity to all characters
    // and creatures. Otherwise, we would have an many dungeons of the same 
    // type, but not the same identity

    // ArrayLists that contains all Characters, Creatures, and Treasures
    protected ArrayList<Character> characterList = new ArrayList<Character>();
    protected ArrayList<Creature> creatureList = new ArrayList<Creature>();
    protected ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    
    Tracker tracker = new Tracker(dungeon, characterList, creatureList, treasureList); // Game Tracker
    // Using the Tracker is an example of the Observer pattern. Events are published to the Tracker (pointed out in comments)
    // And then the Tracker let's any interested parties know about the events.

    protected Printer printer = new Printer(dungeon, tracker, output); // Game Printer

    // Game variables that track win condition
    private int roundCounter = 0; // The Integer round number
    
    private boolean endCondition = true; // End Condition check
    private Scanner scanner = new java.util.Scanner(System.in); // Scanner for user input


    /**
     * @param outputType: String
     * 
     * Constructor to initialize board.
     */
    public GameEngine(String outputType) {
        this.output = outputType;
        if (output != "ShowNone") {
            System.out.println("Starting Game!");
            System.out.println("Press Enter To Continue...");
            scanner.nextLine();
        }   
    }


    /**
     * Run the game by simulating processing each turn which includes
     * Characters and Creatures. Ends if the end condition is completed
     */
    public void runGame() {
        populateEntities(dungeon, tracker);
        while (endCondition) {
            roundCounter++;
            tracker.setRoundCounter(roundCounter); // publish round counter to Tracker
            processTurn();
        }        
        scanner.close();
    }
    

    /**
     * Populate CharacterList and CreatureList with Characters and Creatures
     */
    public void populateEntities(Dungeon dungeon, Tracker tracker) {
        // Example of polymorphism. 
        // In this case we are adding subclasses to an ArrayList
        // but the ArrayList is made of an abstract class
        // All Characters or Creatures behave as the instance
        // of their abstract class.

        int id = 0; // Object ID value

        // Characters
        characterList.add(new Runner(id, dungeon));
        id++;
        characterList.add(new Sneaker(id, dungeon));
        id++;
        characterList.add(new Thief(id, dungeon));
        id++;
        characterList.add(new Brawler(id, dungeon));
        id++;
        tracker.setCharacterStats(characterList); // publish initial Character stats to Tracker
        // Example of Observer pattern, Tracker in turn tells Rooms (subscriber) of new occupancy.

        // Creatures
        // Also an example of polymorphism
        for(int i = 0; i < 4; i++) {
            creatureList.add(new Seeker(id, dungeon));
            id++;
            creatureList.add(new Orbiter(id, dungeon));
            id++;
            creatureList.add(new Blinker(id, dungeon));
            id++;
        }
        tracker.setCreatureStats(creatureList); // publish initial Creature stats to Tracker
        // Again, example of Observer pattern.

        // Treasures
        for(int i = 0; i < 4; i++) {
            treasureList.add(new Sword(id, dungeon));
            id++;
            treasureList.add(new Gem(id, dungeon));
            id++;
            treasureList.add(new Armor(id, dungeon));
            id++;
            treasureList.add(new Portal(id, dungeon));
            id++;
            treasureList.add(new Trap(id, dungeon));
            id++;
            treasureList.add(new Potion(id, dungeon));
            id++;
        }
        tracker.setTreasureStats(treasureList); // publish initial Treasure stats to Tracker
        // Again, example of Observer pattern.
    }
    

    /**
     * @param character: Characters
     * @param creature: Creatures
     * 
     * Input a Character `A` and Creature `B`
     * Deducts health if a dice roll is larger than the other. 
     * 
     * If a character rolls a -1, fight is skipped.
     */
    private void simulateFight(Character character, Creature creature) {
        FightBehavior fightBehavior = character.getFightBehavior();
        Celebration celebration = new SpinCelebration(fightBehavior);
        celebration = new DanceCelebration(celebration);
        celebration = new JumpCelebration(celebration);
        celebration = new ShoutCelebration(celebration);

        int characterRoll = celebration.fight();
        int creatureRoll = creature.fight();

        for(Treasure t: character.getInventory()){
            characterRoll += t.getFightBonus();
            creatureRoll += t.getAdversaryFightBonus();
        }

        if(characterRoll > 0) {
            // If fight not skipped
            if(characterRoll > creatureRoll) {
                // If Character Wins
                tracker.characterWon(character, creature, characterRoll, creatureRoll); // Publish Character won to Tracker
                tracker.removeCreature(creature); // Remove dead Creature, publish to Trackers
                if (output != "ShowNone") {
                    celebration.celebrate();
                    tracker.characterCelebrated(character, celebration); // Publish Character celebrated to Tracker
                }
                // Example of Observer pattern, Tracker let's interested parties/subsribers
                // (Printer, Logger, Room)'s know about these events.
            } else if (characterRoll < creatureRoll) {
                // If Creature Wins
                tracker.creatureWon(character, creature, characterRoll, creatureRoll); // Publish Creature won to Tracker
                if (character.getHealth() <= 0) {
                    tracker.removeCharacter(character); // Remove dead Character, publish to Tracker
                }
            }
        } else {
            // If characterRoll = -1, fight was skipped
            tracker.fightSkipped(); // Publish fight skipped to Tracker
        }
        printer.printFightResults();
        // Printer is informed of results to print via the Tracker, example of Observer pattern
    }
    

    /**
     * @param character: Character
     * 
     * Performs the Character action of searching for treasure.
     * Adds to the Characters treasure count
     */
    private void simulateTreasure(Character character) {
        int neededScore = character.getSearchBehavior().getNeededScore();
        int score = character.searchTreasure();

        ArrayList<Treasure> treasureInRoom = character.getLocation().getTreasuresInRoom();
        if (!treasureInRoom.isEmpty()) {
            // If there is Treasure in the room
            if (score >= neededScore) {
                // If Treasure found
                Treasure currentItem = treasureInRoom.get(0); // only find first Treasure (if multiple)
                // Possible "feature" if Character has Treasure of first type already but not of second, Character still doesn't get the second Treasure.
                if (character.getInventoryTypes().contains(currentItem.getType())) {
                    // If we've already encountered this type of Treasure
                    if (currentItem.getType() == "Trap") {
                        // Can only encounter multiple traps
                        character.addInventory(currentItem);
                        character.loseHealth(currentItem.getTakeDamage()); // if Trap
                        tracker.treasureFound(currentItem, score); // Publish Treasure found to Tracker
                        if (character.getHealth() <= 0) {
                            tracker.removeCharacter(character); // Remove dead Character, publish to Tracker
                        } 
                    } else {
                        tracker.duplicateTreasureFind(currentItem, score); // Publish to Tracker that duplicate item was found
                    }
                } else {
                    // This is a new type of Treasure
                    character.addInventory(currentItem);
                    character.loseHealth(currentItem.getTakeDamage()); // if Trap
                    character.addHealth(currentItem.getHPBoost());  // if Potion
                    tracker.treasureFound(currentItem, score); // Publish Treasure found to Tracker
                }
            } else {
                // If Treasure not found
                tracker.treasureNotFound(score);
            }
        } else {
            // If no Treasure in room
            tracker.treasureNotFound(score);
        }
        printer.printTreasureHuntResults(); // Example of Observer Pattern
        // Printer knows results to print from Tracker
    }
    
    
    /**
     * Processes the turns for each Character and for each Creature.
     */
    private void processTurn() {
        Logger logger = new Logger(tracker, output);
        // Process Characters
        for (int i = 0; i < characterList.size(); i++) { // Changing to this type of loop to avoid comodification
        Character character =  characterList.get(i);
            if (endCondition) {
                // Stops processing Characters if end condition is met
                process1Character(character); // Process character
                checkWinCondition(); // Updates win conditions}

                // Printing
                if (output == "OneScreen") {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
                if (output == "OneScreen" || output == "ShowAll") {
                    printer.printDungeon();
                    printer.pause();
                }
            } else {
                break;
            }
        }

        // Process Creatures
        for (int i = 0; i < creatureList.size(); i++) { // Changing to this type of loop to avoid comodification
            Creature creature = creatureList.get(i);
            if (endCondition) {
                // Stops processing Creatures if end condition is met
                process1Creature(creature);
                checkWinCondition();

                // Printing
                if (output == "OneScreen") {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
                if (output == "OneScreen" || output == "ShowAll") {
                    printer.printDungeon();
                    printer.pause();
                }
            } else {
                break;
            }
        }
        logger.logRound();
    }


    /**
     * @param character: Character
     * 
     * This method processes the decision making for one Character:
     * - If a Creature is in the Room, it automatically fights.
     * - If no other Creature is in the Room, the Character randomly moves.
     * - In new Room, the Character searches for treasure if there are no Creatures
     *   or fights if there are Creatures.
     */
    private void process1Character(Character character) {
        // Process turn counts for characters. Mostly 1 but runners have 2
        for (int i = 0; i < character.getMoveCount(); i++) {
            // Move to new Room
            Room oldRoom = character.getLocation();
            character.checkPortalInInventory(); // Check if Character will Blink or RandomWalk
            character.move();
            Room newRoom = character.getLocation();
            tracker.characterMoved(character, oldRoom, newRoom); // Publish Character moved to Tracker

            // Look for creatures
            ArrayList<Creature> creaturesInRoom = newRoom.getCreaturesInRoom();
            if (creaturesInRoom.size() > 0) {
                // If there are Creatures in the room, fight
                for (int j = 0; j < creaturesInRoom.size(); j++) {
                    Creature creature = creaturesInRoom.get(j);
                    simulateFight(character, creature);
                }
                continue;
            } else {
                // If there are no Creatures in the room, look for treasure
                simulateTreasure(character);
            }
        }
    }
    

    /**
     * @param creature: Creature
     * 
     * Processes the decision making for one Creature:
     * - If a Character is in the Room, it automatically fights.
     * - If no other Character is in the Room, move.
     */
    private void process1Creature(Creature creature) {
        // Get Room information and Characters in the Room
        Room oldRoom = creature.getLocation();

        ArrayList<Character> charactersInOldRoom = oldRoom.getCharactersInRoom();
        if (charactersInOldRoom.size() > 0) {
            // If there is a character, don't move, fight!
            for (int i = 0; i < charactersInOldRoom.size(); i++) {
                Character character = charactersInOldRoom.get(i);
                simulateFight(character, creature);
            }
        } else{
            // If no character, move
            creature.move();
            Room newRoom = creature.getLocation();
            tracker.creatureMoved(creature, oldRoom, newRoom); // Publish Creature moved to Tracker
            
            // If characters in new room, fight
            ArrayList<Character> charactersInNewRoom = newRoom.getCharactersInRoom();
            for (int i = 0; i < charactersInNewRoom.size(); i++) {
                Character character = charactersInNewRoom.get(i);
                simulateFight(character, creature);
            }
        }
    }
    
    
    /**
     * Checks various end game conditions and modifies EndCondition accordingly.
     */
    public void checkWinCondition() {
        int treasureCount = tracker.getTreasureCount();
        int creatureCount = tracker.getCreatureList().size();
        int characterCount = tracker.getCharacterList().size();

        // Change End Condition depending on the outcome
        if (treasureCount == 24) { 
            // 24 Treasures Found
            endCondition = false;
            System.out.println("Game Over");
            System.out.println("All treasure found");
            System.out.println("\n");
        } else if (creatureCount <= 0) { 
            //All Creatures eliminated
            endCondition = false;
            System.out.println("Game Over");
            System.out.println("All Creatures eliminated");
            System.out.println("\n");
        } else if (characterCount <= 0) {
            //All Characters defeated
            endCondition = false;
            System.out.println("Game Over");
            System.out.println("All Adventurers eliminated");
            System.out.println("\n");
        } else {
            endCondition = true;
        }
    }
}
