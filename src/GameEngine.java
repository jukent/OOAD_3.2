import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {

    // ArrayList that contains all characters and Creatures
    protected ArrayList<Characters> CharacterList = new ArrayList<Characters>();
    protected ArrayList<Creatures> CreatureList = new ArrayList<Creatures>();
    protected ArrayList<Treasure> TreasureList = new ArrayList<Treasure>();
    protected ArrayList<Subscriber> SubscriberList = new ArrayList<Subscriber>();
    private String Output = "ShowAll"; // OneScreen,ShowEnding,ShowAll
    protected Dungeon dungeon = new Dungeon(); // Example of identity
    protected Printer printer = new Printer(dungeon, Output);

    // Dungeon is an example of identity. While we could create an instance
    // of dungeon in each character, by having the same instance of dungeon
    // passed to the characters, we can assure that each character
    // has a reference to the same map. This eliminates any issues that may come
    // from specific map generations. We pass its identity to all characters
    // and creatures. Otherwise, we would have an many dungeons of the same 
    // type, but not the same identity

    // Game variables that track win condition
    private int TreasureCount = 0;
    private int DeadTreasureCount = 0;
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
        printer = new Printer(dungeon, Output);
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
        CharacterList.add(new Runners(ID, dungeon));
        ID++;
        CharacterList.add(new Sneakers(ID, dungeon));
        ID++;
        CharacterList.add(new Thieves(ID, dungeon));
        ID++;
        CharacterList.add(new Brawlers(ID, dungeon));
        ID++;

        // Creatures
        // Also an example of polymorphism
        for(int i = 0; i <4; i++) {
            CreatureList.add(new Seekers(ID, dungeon));
            ID++;
            CreatureList.add(new Orbiters(ID, dungeon));
            ID++;
            CreatureList.add(new Blinkers(ID, dungeon));
            ID++;
        }

        for(int i = 0; i<4; i++) {
            TreasureList.add(new Sword(ID, dungeon));
            ID++;
            TreasureList.add(new Gem(ID, dungeon));
            ID++;
            TreasureList.add(new Armor(ID, dungeon));
            ID++;
            TreasureList.add(new Portal(ID, dungeon));
            ID++;
            TreasureList.add(new Trap(ID, dungeon));
            ID++;
            TreasureList.add(new Potion(ID, dungeon));
            ID++;
        }
        
        // Tell Rooms that there are occupants.
        setOccupancy();
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
                if(Output != "ShowNone"){
                System.out.print("Fight: ");
                System.out.print(A.getClass().getSimpleName() + ": ");
                System.out.print(CharacterRoll);
                System.out.print(" " + B.getClass().getSimpleName() + ": ");
                System.out.print(CreatureRoll);
                System.out.println(" Creature Wins :( ");}
            }
        } else {
            if(Output != "ShowNone") {
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

        ArrayList<Treasure> treasure_in_room = A.Location.getTreasureInRoom();

        if(Score >= NeededScore) {
            //A.CurrentTreasure;
            if (Output != "ShowNone" && !treasure_in_room.isEmpty()) {
                System.out.print("Treasure Hunt: ");
                System.out.print(Score);
                System.out.println(" Success! ");
                System.out.println("Treasure: " + treasure_in_room.get(0).getClass());

                Treasure CurrentItem = treasure_in_room.get(0);
                CurrentItem.setFound(true);
                if (A.InventoryTypes.contains(CurrentItem.getType())) {
                    A.setInventory(CurrentItem);
                    A.loseHealth(CurrentItem.getTakeDamage());
                    if (CurrentItem.getType() == "Trap") {
                        treasure_in_room.remove(0);
                    }
                } else {
                    treasure_in_room.remove(0);
                    A.Inventory.add(CurrentItem);
                    A.InventoryTypes.add(CurrentItem.getType());
                    A.loseHealth(CurrentItem.getTakeDamage());
                    A.addHealth(CurrentItem.getHPBoost()); 
                }
                A.getLocation().setTreasureInRoom(treasure_in_room);
            }
        } 
        if (Score == -1) {
            if (Output != "ShowNone") {
                System.out.print("Treasure Hunt: ");
                System.out.println("Search Skipped!");
            }
        } else {
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
        // Process Characters
        for(Characters I: CharacterList) {
            if (EndCondition) {
                //Stops processing Characters if end condition is met
                if (Output == "OneScreen") {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
                if (Output == "OneScreen" || Output == "ShowAll") {
                    showGameStatus();
                    printer.printDungeon();
                    printCharacterStats();
                    printCreatureStats();
                    System.out.println();
                }

                setOccupancy(); // Update room occupancy
                process1Character(I); // Process character
                checkWinCondition(); // Updates win conditions}
                printer.pause();
            } else {
                break;
            }
        }

        // Process Creatures
        for (Creatures I: CreatureList) {
            if (EndCondition) {
                // Stops processing Creatures if end condition is met
                if (Output == "OneScreen") {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();}
                if (Output == "OneScreen" || Output == "ShowAll") {
                    showGameStatus();
                    printer.printDungeon();
                    printCharacterStats();
                    printCreatureStats();
                    System.out.println();
                }

                setOccupancy();
                process1Creature(I);
                checkWinCondition();
                printer.pause();
            } else {
                break;
            }
        }
    }
    

    /**
     * @param room: Room
     * 
     * Method to get Characters from a particular room.
     */
    private void setCharactersInRoom(Room room) {
        ArrayList<Characters> characters_in_room = new ArrayList<>();
        for (Characters c: CharacterList) {
            Room character_location = c.getLocation();
            if (character_location == room) {
                characters_in_room.add(c);
            } 
        }
        room.setCharactersInRoom(characters_in_room);  
    }


    /**
     * @param room: Room
     * 
     * Method to get Creatures from a particular room.
     */
    private void setCreaturesInRoom(Room room) {
        ArrayList<Creatures> creatures_in_room = new ArrayList<>();
        for (Creatures c: CreatureList) {
            Room creature_location = c.getLocation();
            if (creature_location == room) {
                creatures_in_room.add(c);
            } 
        }
        room.setCreaturesInRoom(creatures_in_room);  
    }


        /**
     * @param room: Room
     * 
     * Method to get Treasure from a particular room.
     */
    private void setTreasureInRoom(Room room) {
        ArrayList<Treasure> treasure_in_room = new ArrayList<>();
        for (Treasure t: TreasureList) {
            Room treasure_location = t.getLocation();
            if (treasure_location == room) {
                treasure_in_room.add(t);
            } 
        }
        room.setTreasureInRoom(treasure_in_room);  
    }


    /**
     * Method to get Characters, Creatures, and Treasures in each Room of the Dungeon.
     */
    private void setOccupancy() {
        for (Room r: this.dungeon.getMap().values()) {
            setCharactersInRoom(r);
            setCreaturesInRoom(r); 
            setTreasureInRoom(r);
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
            ArrayList<Treasure> treasure_in_room = A.Location.getTreasureInRoom();
            setOccupancy();

            // Look for creatures
            ArrayList<Creatures> creatures_in_room = new_room.getCreaturesInRoom();
            if (creatures_in_room.size() > 0) {
                // If there are Creatures in the room, fight
                for (Creatures c:creatures_in_room) {
                    simulateFight(A, c);
                }
                continue;
            } 
            else if (!treasure_in_room.isEmpty() && !treasure_in_room.get(0).Found) {
                // If there are no Creatures (and not in the starting room), look for treasure
                simulateTreasure(A);
                break;
            } else {
                break;
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
        // Get Room information and Rharacters in the Room
        Room current_room = A.getLocation();
        setOccupancy();
        ArrayList<Characters> characters_in_room = current_room.getCharactersInRoom();
        
        if (characters_in_room.size() > 0) {
            // If there is a character, don't move, fight!
            for (Characters c: characters_in_room) {
                simulateFight(c, A);
            }
        } else{
            // If no character, move
            A.move();
            Room new_room = A.getLocation();
            setOccupancy();
            
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
        int TC = 0;
        boolean all_treasures_found = true;

        ArrayList<Characters> TempCharacterList = new ArrayList<Characters>();
        ArrayList<Creatures> TempCreatureList = new ArrayList<Creatures>();

        // Removes Creatures from board if it runs out of health
        for (Creatures I: CreatureList) {
            if (I.getHealth() > 0) {
                TempCreatureList.add(I);
            }
        }
        CreatureList = TempCreatureList;

        // Removes Character from board if it runs out of health
        // Only counts treasures if player is alive
        for (Characters I: CharacterList) {
            if (I.getHealth() > 0) {
                TempCharacterList.add(I);
                TC += I.getTreasureCount();
            } else if (I.getHealth() <= 0) {
                DeadTreasureCount += I.getTreasureCount();
            }
        }
        CharacterList = TempCharacterList;
        setOccupancy(); // Remove dead creatures and characters

        // Update game tracking variables
        TreasureCount = TC + DeadTreasureCount;
        CreatureCount = CreatureList.size();
        CharacterCount = CharacterList.size();

        for(Treasure I: TreasureList){
            if(!I.Found){
                all_treasures_found = false;
            }
        }

        // Change End Condition depending on the outcome
        if (all_treasures_found) { 
            // 10 Treasures Found
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("all treasure found");
        } else if (CreatureCount <= 0) { 
            //All Creatures eliminated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("all creatures eliminated");
        } else if (CharacterCount <= 0) {
            //All Characters defeated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println(" all Adventurers eliminated");
        } else {
            EndCondition = true;
        }
    }


    /**
     * Shows an overview of game information such as win conditions and entitys
     */
    private void showGameStatus() {
        System.out.print("Game Status: ");
        System.out.print(" Round: ");
        System.out.print(RoundCounter);
        System.out.print(" Characters: ");
        System.out.print(CharacterCount);
        System.out.print(" Creatures: ");
        System.out.print(CreatureCount);
        System.out.print(" Treasures Collected: ");
        System.out.println(TreasureCount);
    }


    /**
     * This method prints Character stats: name, treausres, hp.
     */
    private void printCharacterStats(){
        String tbl_header = new String("Adventurers\tRoom\tDamage\tTreasure");
        System.out.println(tbl_header);
        for (Characters c: CharacterList) {
            String name = c.getName();
            String room = c.getLocation().getName();
            //Integer g = c.getTreasure();
            //Integer hp = 3-c.getHealth();
            Integer damage = c.getHealth();
            String treasure = c.getInventoryString();

            String char_stats = new String(name + "\t\t" + room + "\t" + damage + "\t" + treasure);
            System.out.println(char_stats);
        }
    }


    /**
     * This method prints Creature stats: name and number remaining.
     */
    private void printCreatureStats() {
        String tbl_header = new String("Creatures\tRoom");
        System.out.println(tbl_header);
  
        for (Creatures c: CreatureList) {
            String name = c.getName();
            String room = c.getLocation().getName();
            String creat_stats = new String(name + "\t\t" + room);
            System.out.println(creat_stats);
        }
    }
}
