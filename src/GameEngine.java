import java.util.ArrayList;
import java.util.Scanner;

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
    protected ArrayList<Characters> characterList = new ArrayList<Characters>();
    protected ArrayList<Creatures> creatureList = new ArrayList<Creatures>();
    protected ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    
    Tracker tracker = new Tracker(dungeon, characterList, creatureList, treasureList);

    protected Printer printer = new Printer(dungeon, tracker, Output);



    // Game variables that track win condition
    //private int treasureCount = 0;
    //private int deadTreasureCount = 0;
    private int CreatureCount = 1;
    private int CharacterCount = 0;
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
        printer = new Printer(dungeon, tracker, Output);
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
        characterList.add(new Runners(ID, dungeon));
        ID++;
        characterList.add(new Sneakers(ID, dungeon));
        ID++;
        characterList.add(new Thieves(ID, dungeon));
        ID++;
        characterList.add(new Brawlers(ID, dungeon));
        ID++;
        tracker.setCharacterStats(characterList);

        // Creatures
        // Also an example of polymorphism
        for(int i = 0; i <4; i++) {
            creatureList.add(new Seekers(ID, dungeon));
            ID++;
            creatureList.add(new Orbiters(ID, dungeon));
            ID++;
            creatureList.add(new Blinkers(ID, dungeon));
            ID++;
        }
        tracker.setCreatureStats(creatureList);

        for(int i = 0; i<4; i++) {
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
    private void simulateFight(Characters A, Creatures B) {
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
                B.loseHealth(1);
                tracker.setCreatureStats(creatureList); // Remove dead creature

                if (Output != "ShowNone") {
                    System.out.print("Fight: ");
                    System.out.print(A.getClass().getSimpleName() + ": ");
                    System.out.print(CharacterRoll);
                    System.out.print(" " + B.getClass().getSimpleName() + ": ");
                    System.out.print(CreatureRoll);
                    System.out.println(" " + A.getClass().getSimpleName() + " Wins :D ");
                    System.out.print(A.getName() + " celebrates!: ");
                    c1.celebrate();
                    System.out.println();
                }
            } else if (CharacterRoll < CreatureRoll) {
                A.loseHealth(1);
                tracker.setCharacterStats(characterList); // Update hp or remove dead adventurer

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
    private void simulateTreasure(Characters A) {
        int NeededScore = A.HuntBehavior.NeededScore;
        int Score = A.searchTreasure();

        ArrayList<Treasure> treasure_in_room = tracker.getTreasureInRoom(A.Location);
        if (!treasure_in_room.isEmpty()) {
            // If there is Treasure in the room
            if (Score >= NeededScore) {
                // If Treasure found
                Treasure currentItem = treasure_in_room.get(0);
                //currentItem.setFound(true);

                if (Output != "ShowNone") {
                    // If printing
                    System.out.print("Treasure Hunt: ");
                    System.out.print(Score);
                    System.out.println(" Success! ");
                    System.out.println("Treasure: " + treasure_in_room.get(0).getClass());
                }
                if (A.InventoryTypes.contains(currentItem.getType())) {
                    // If we've already encountered this type of Treasure
                    if (currentItem.getType() == "Trap") {
                        // Can encounter multiple traps
                        A.setInventory(currentItem);
                        A.loseHealth(currentItem.getTakeDamage()); 
                        tracker.setCharacterStats(characterList);
                        tracker.removeTreasure(currentItem);
                        tracker.increaseTreasureCount(1);
                    }
                } else {
                    // This is a new type of Treasure
                    A.InventoryTypes.add(currentItem.getType());
                    A.loseHealth(currentItem.getTakeDamage());
                    A.addHealth(currentItem.getHPBoost()); 
                    tracker.setCharacterStats(characterList);
                    tracker.removeTreasure(currentItem);
                    tracker.increaseTreasureCount(1);
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
        // Process Characters
        for(Characters I: characterList) {
            if (EndCondition) {
                // Stops processing Characters if end condition is met
                process1Character(I); // Process character
                checkWinCondition(); // Updates win conditions}
            } else {
                break;
            }
        }

        // Print Logger at end of each all-character turn -- move this to Logger
        if (Output == "OneScreen") {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        if (Output == "OneScreen" || Output == "ShowAll") {
            //showGameStatus();
            printer.printDungeon();
            logger.printLog();
            System.out.println();
            //printer.pause();
        } 

        // Process Creatures
        for (Creatures I: creatureList) {
            if (EndCondition) {
                // Stops processing Creatures if end condition is met
                process1Creature(I);
                checkWinCondition();
            } else {
                break;
            }
        }

        // Print Logger at end of each all-character turn -- move this to Logger
        if (Output == "OneScreen") {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        if (Output == "OneScreen" || Output == "ShowAll") {
            //showGameStatus();
            printer.printDungeon();
            logger.printLog();
            printer.pause();
        }
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
    private void process1Character(Characters A) {
 
        //Process turn counts for characters. Mostly 1 but runners have 2
        for (int i = 0; i < A.MoveCount; i++) {
            // Move to new Room
            A.move();
            Room new_room = A.getLocation();
            tracker.setCharacterStats(characterList);

            // Look for creatures
            ArrayList<Creatures> creatures_in_room = tracker.getCreaturesInRoom(new_room);
            if (creatures_in_room.size() > 0) {
                // If there are Creatures in the room, fight
                for (Creatures c:creatures_in_room) {
                    simulateFight(A, c);
                }
                continue;
            } else {
                // If there are no Creatures in the room, look for treasure
                // Unintelligent (no knowledge of if there IS treasure)
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
    private void process1Creature(Creatures A){
        // Get Room information and Characters in the Room
        Room current_room = A.getLocation();
        ArrayList<Characters> characters_in_room = tracker.getCharactersInRoom(current_room);
        
        if (characters_in_room.size() > 0) {
            // If there is a character, don't move, fight!
            for (Characters c: characters_in_room) {
                simulateFight(c, A);
            }
        } else{
            // If no character, move
            A.move();
            Room new_room = A.getLocation();
            tracker.setCreatureStats(creatureList);
            
            // If characters in new room, fight
            ArrayList<Characters> characters_in_new_room = new_room.getCharactersInRoom();
            for (Characters c: characters_in_new_room) {
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
            System.out.println("all treasure found");
        } else if (creatureCount <= 0) { 
            //All Creatures eliminated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("all creatures eliminated");
        } else if (characterCount <= 0) {
            //All Characters defeated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println(" all Adventurers eliminated");
        } else {
            EndCondition = true;
        }
    }
}
