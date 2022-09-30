import java.util.ArrayList;
import java.util.Scanner;

import Dungeon.Dungeon;
import Dungeon.Room;

import Entity.Character.Character;
import Entity.Character.Brawler;
import Entity.Character.Runner;
import Entity.Character.Sneaker;
import Entity.Character.Thief;

import Entity.Creature.Creature;
import Entity.Creature.Blinker;
import Entity.Creature.Orbiter;
import Entity.Creature.Seeker;

import Treasure.Treasure;
import Treasure.Armor;
import Treasure.Gem;
import Treasure.Portal;
import Treasure.Potion;
import Treasure.Sword;
import Treasure.Trap;

import FightingBehavior.FightBehavior;

import Celebration.Celebration;
import Celebration.DanceCelebration;
import Celebration.JumpCelebration;
import Celebration.ShoutCelebration;
import Celebration.SpinCelebration;

import Printer.Printer;

import Tracker.Tracker;
import Tracker.Logger;

public class GameEngine {

    private String Output = "ShowAll"; // OneScreen,ShowEnding,ShowAll

    protected Dungeon dungeon = new Dungeon(); // Example of identity
    // Dungeon is an example of identity. While we could create an instance
    // of dungeon in each character, by having the same instance of dungeon
    // passed to the characters, we can assure that each character
    // has a reference to the same map. This eliminates any issues that may come
    // from specific map generations. We pass its identity to all characters
    // and creatures. Otherwise, we would have an many dungeons of the same 
    // type, but not the same identity

    // ArrayList that contains all characters and Creatures
    protected ArrayList<Character> characterList = new ArrayList<Character>();
    protected ArrayList<Creature> creatureList = new ArrayList<Creature>();
    protected ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    
    Tracker tracker = new Tracker(dungeon, characterList, creatureList, treasureList);

    protected Printer printer = new Printer(dungeon, tracker, Output);

    // Game variables that track win condition
    private int RoundCounter = 0;
    private int ID = 0;
    
    private boolean EndCondition = true;
    private Scanner A = new java.util.Scanner(System.in);


    /**
     * @param OutputType: String
     * 
     * Constructor to initialize board.
     */
    public GameEngine(String OutputType) {
        Output = OutputType;
        populateEntities();
        if (Output != "ShowNone") {
            System.out.println("Starting Game!");
            System.out.println("Press Enter To Continue...");
            A.nextLine();
        }   
    }


    /**
     * Run the game by simulating processing each turn which includes
     * Characters and Creatures. Ends if the end condition is completed
     */
    public void runGame() {
        while (EndCondition) {
            RoundCounter++;
            tracker.setRoundCounter(RoundCounter);
            processTurn();
        }        
        A.close();
    }
    

    /**
     * Populate CharacterList and CreatureList with Characters and Creatures
     */
    private void populateEntities() {
        // Example of polymorphism. 
        // In this case we are adding subclasses to an ArrayList
        // but the ArrayList is made of an abstract class
        // All Characters or Creatures behave as the instance
        // of their abstract class.

        // Characters
        characterList.add(new Runner(ID, dungeon));
        ID++;
        characterList.add(new Sneaker(ID, dungeon));
        ID++;
        characterList.add(new Thief(ID, dungeon));
        ID++;
        characterList.add(new Brawler(ID, dungeon));
        ID++;
        tracker.setCharacterStats(characterList);
        // Unclear if observer pattern makes it so the game engine doesn't manage the character or creature lists at all
        // but creates events for the tracker to manage these lists, and the game engine pulls from the tracker
        // or if the tracker should simply "observe" and store information -- seems less useful.
        // go to office hours thursday 1:30-3
        // game engine and tracker are subscribers?
        // also information is already stored in Character objects, should tracker maintain a separate data table? Seems cumbersome, when can just point to characters
        // 

        // Creatures
        // Also an example of polymorphism
        for(int i = 0; i < 4; i++) {
            creatureList.add(new Seeker(ID, dungeon));
            ID++;
            creatureList.add(new Orbiter(ID, dungeon));
            ID++;
            creatureList.add(new Blinker(ID, dungeon));
            ID++;
        }
        tracker.setCreatureStats(creatureList);

        for(int i = 0; i < 4; i++) {
            treasureList.add(new Sword(ID, dungeon));
            ID++;
            treasureList.add(new Gem(ID, dungeon));
            ID++;
            treasureList.add(new Armor(ID, dungeon));
            ID++;
            treasureList.add(new Portal(ID, dungeon));
            ID++;
            treasureList.add(new Trap(ID, dungeon));
            ID++;
            treasureList.add(new Potion(ID, dungeon));
            ID++;
        }
        tracker.setTreasureStats(treasureList);
    }
    

    /**
     * @param A: Characters
     * @param B: Creatures
     * 
     * Input a Character `A` and Creature `B`
     * Deducts health if a dice roll is larger than the other. 
     * 
     * If a character rolls a -1, fight is skipped.
     */
    private void simulateFight(Character A, Creature B) {
        FightBehavior f1 = A.FightBehavior;
        Celebration c1 = new SpinCelebration(f1);
        c1 = new DanceCelebration(c1);
        c1 = new JumpCelebration(c1);
        c1 = new ShoutCelebration(c1);

        int CharacterRoll = c1.fight();
        int CreatureRoll = B.fight();

        for(Treasure I: A.Inventory){
            CharacterRoll += I.getFB();
            CreatureRoll += I.getAFB();
        }

        if(CharacterRoll > 0) {
            if(CharacterRoll > CreatureRoll) {
                // Character Wins
                tracker.characterWon(A, B);
                tracker.removeCreature(B);

                if (Output != "ShowNone") {
                    System.out.print("Fight: ");
                    System.out.print(A.getClass().getSimpleName() + ": ");
                    System.out.print(CharacterRoll);
                    System.out.print(" " + B.getClass().getSimpleName() + ": ");
                    System.out.print(CreatureRoll);
                    System.out.println(" " + A.getClass().getSimpleName() + " Wins :D ");
                    System.out.print(A.getName() + " celebrates!: ");
                    c1.celebrate();
                    tracker.characterCelebrated(A, c1);
                    System.out.println();
                }
            } else if (CharacterRoll < CreatureRoll) {
                // Creature Wins
                tracker.creatureWon(A, B);
                if (A.getHealth() <= 0) {
                    tracker.removeCharacter(A); // Remove dead character
                }

                if (Output != "ShowNone") {
                    System.out.print("Fight: ");
                    System.out.print(A.getClass().getSimpleName() + ": ");
                    System.out.print(CharacterRoll);
                    System.out.print(" " + B.getClass().getSimpleName() + ": ");
                    System.out.print(CreatureRoll);
                    System.out.println(" Creature Wins :( ");
                }
            }
        } else {
            // Tie
            if (Output != "ShowNone") {
                System.out.println("Fight Skipped");
            }
        }
    }
    

    /**
     * @param A: Character
     * 
     * Performs the Character action of searching for treasure.
     * Adds to the Characters treasure count
     */
    private void simulateTreasure(Character A) {
        int NeededScore = A.HuntBehavior.NeededScore;
        int Score = A.searchTreasure();

        //ArrayList<Treasure> treasure_in_room = tracker.getTreasuresInRoom(A.Location);
        ArrayList<Treasure> treasure_in_room = A.getLocation().getTreasuresInRoom();
        if (!treasure_in_room.isEmpty()) {
            // If there is Treasure in the room
            if (Score >= NeededScore) {
                // If Treasure found
                Treasure currentItem = treasure_in_room.get(0); // only find first treasure (if multiple)
                // Possible "feature" if character has treasure of type treasures_in_room[0] already but not treasures_in_room[1], character still doesn't get treasures[1]

                if (Output != "ShowNone") {
                    // If printing
                    System.out.print("Treasure Hunt: ");
                    System.out.print(Score);
                    System.out.println(" Success! ");
                    System.out.println("Treasure: " + treasure_in_room.get(0).getClass());
                }
                if (A.getInventoryTypes().contains(currentItem.getType())) {
                    // If we've already encountered this type of Treasure
                    if (currentItem.getType() == "Trap") {
                        // Can only encounter multiple traps
                        A.addInventory(currentItem);
                        A.loseHealth(currentItem.getTakeDamage()); // if Trap
                        tracker.treasureFound(currentItem);
                        if (A.getHealth() <= 0) {
                            tracker.removeCharacter(A); // Remove dead character
                        } 
                    }
                } else {
                    // This is a new type of Treasure
                    A.addInventory(currentItem);
                    A.loseHealth(currentItem.getTakeDamage()); // if Trap
                    A.addHealth(currentItem.getHPBoost());  // if Potion
                    tracker.treasureFound(currentItem);
                }
            } else {
                // If Treasure not found
                if (Output != "ShowNone") {
                    // If Printing
                    System.out.print("Treasure Hunt: ");
                    System.out.print(Score);
                    System.out.println(" Fail :(");
                }
            }
        } else {
            // If no Treasure in room
            if (Output != "ShowNone") {
                System.out.print("Treasure Hunt: ");
                System.out.print(Score);
                System.out.println(" Fail :(");
            }
        }
    }
    
    
    /**
     * Processes the turns for each Character and for each Creature.
     */
    private void processTurn() {
        Logger logger = new Logger(tracker);
        printer = new Printer(dungeon, tracker, Output);
        // Process Characters
        for (int i = 0; i < characterList.size(); i++) { // Changing to this type of loop to avoid comodification
        Character I =  characterList.get(i);
            if (EndCondition) {
                // Stops processing Characters if end condition is met
                process1Character(I); // Process character
                checkWinCondition(); // Updates win conditions}

                // Printing
                if (Output == "OneScreen") {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
                if (Output == "OneScreen" || Output == "ShowAll") {
                    printer.printDungeon();
                    printer.pause();
                }
            } else {
                break;
            }
        }

        // Process Creatures
        for (int i = 0; i < creatureList.size(); i++) { // Changing to this type of loop to avoid comodification
            Creature I = creatureList.get(i);
            if (EndCondition) {
                // Stops processing Creatures if end condition is met
                process1Creature(I);
                checkWinCondition();

                // Printing
                if (Output == "OneScreen") {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
                if (Output == "OneScreen" || Output == "ShowAll") {
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
     * @param A: Character
     * 
     * This method processes the decision making for one Character:
     * - If a Creature is in the Room, it automatically fights.
     * - If no other Creature is in the Room, the Character randomly moves.
     * - In new Room, the Character searches for treasure if there are no Creatures
     *   or fights if there are Creatures.
     */
    private void process1Character(Character A) {
        // Process turn counts for characters. Mostly 1 but runners have 2
        for (int i = 0; i < A.getMoveCount(); i++) {
            // Move to new Room
            Room old_room = A.getLocation();
            A.checkPortalInInventory();
            A.move();
            Room new_room = A.getLocation();
            tracker.characterMoved(A, old_room, new_room);

            // Look for creatures
            ArrayList<Creature> creatures_in_room = new_room.getCreaturesInRoom();
            if (creatures_in_room.size() > 0) {
                // If there are Creatures in the room, fight
                for (int j = 0; j < creatures_in_room.size(); j++) {
                    Creature c = creatures_in_room.get(j);
                    simulateFight(A, c);
                }
                continue;
            } else {
                // If there are no Creatures in the room, look for treasure
                simulateTreasure(A);
            }
        }
    }
    

    /**
     * @param A: Creature
     * 
     * Processes the decision making for one Creature:
     * - If a Character is in the Room, it automatically fights.
     * - If no other Character is in the Room, move.
     */
    private void process1Creature(Creature A) {
        // Get Room information and Characters in the Room
        Room old_room = A.getLocation();

        ArrayList<Character> characters_in_old_room = old_room.getCharactersInRoom();
        if (characters_in_old_room.size() > 0) {
            // If there is a character, don't move, fight!
            for (int i = 0; i < characters_in_old_room.size(); i++) {
                Character c = characters_in_old_room.get(i);
                simulateFight(c, A);
            }
        } else{
            // If no character, move
            A.move();
            Room new_room = A.getLocation();
            tracker.creatureMoved(A, old_room, new_room);
            
            // If characters in new room, fight
            ArrayList<Character> characters_in_new_room = new_room.getCharactersInRoom();
            for (int i = 0; i < characters_in_new_room.size(); i++) {
                Character c = characters_in_new_room.get(i);
                simulateFight(c, A);
            }
        }
    }
    
    
    /**
     * Checks various end game conditions and modifies EndCondition accordingly
     */
    private void checkWinCondition() {
        int treasureCount = tracker.getTreasureCount();
        int creatureCount = tracker.getCharacterList().size();
        int characterCount = tracker.getCreatureList().size();

        // Change End Condition depending on the outcome
        if (treasureCount == 24) { 
            // 24 Treasures Found
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("All treasure found");
        } else if (creatureCount <= 0) { 
            //All Creatures eliminated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("All Creatures eliminated");
        } else if (characterCount <= 0) {
            //All Characters defeated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("All Adventurers eliminated");
        } else {
            EndCondition = true;
        }
    }
}
