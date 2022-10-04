package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Creature;
import entity.Character;
import celebration.Celebration;

public class Printer {

    private Dungeon dungeon; // Game Dungeon
    private Tracker tracker; // Game Tracker
    private String outputType; // Output options: OneScreen, ShowAll, ShowEnding, ShowNone
    private Scanner scanner = new java.util.Scanner(System.in); // A Scanner for awaiting user input

    
    /**
     * @param dungeon Dungeon
     * @param output String
     * @param tracker Tracker
     * 
     * Construct the printer.
     */
    public Printer(Dungeon dungeon, Tracker tracker, String output) {
        this.dungeon = dungeon; // Game Dungeon
        this.tracker = tracker; // Game Tracker
        this.outputType = output; // Output type String
    }


    /**
     * Print the Dungeon and its occupancy.
     */
    public void printDungeon() {
        if (outputType != "ShowNone") {
            // Print Game Status
            printGameStatus();

            // Level 0 
            Room startingRoom = dungeon.getRoom("(0-1-1)");
            ArrayList<String> occupancyArray = getOccupancyStringArray(startingRoom);
            String occupancyString = occupancyArray.toString().replace("[", "").replace("]", "").replace(",", "");
            System.out.println(occupancyString);

            // Levels 1, 2, 3, 4
            PrinterColumns columns = new PrinterColumns();
            for (int l = 1; l <= 4; ++l) {
                addLevelArray(l, columns);
            }
            columns.print();

            // Print Character/Creature Status
            printCharacterStats();
            printCreatureStats();
        }
    }


    /**
     * A pause method between turns asking the player to continue.
     */
    public void pause() {
        if (outputType == "OneScreen") {
            System.out.println("Press Enter To Continue...");
            scanner.nextLine();
        }
    }


    /**
     * @param room Room
     * @return ArrayList<String>
     *
     * This method gets the string for displaying occupancy in each Room.
     */
    private ArrayList<String> getOccupancyStringArray(Room room) {
        // Characters in Room
        ArrayList<Character> charactersInRoom = room.getCharactersInRoom();
        String characterString = new String();
        for (Character c:charactersInRoom) {
            characterString += c.getName();
            characterString += " ";
        }

        // Creatures in Room
        ArrayList<Creature> creaturesInRoom = room.getCreaturesInRoom();
        String creatureString = new String();
        for (Creature c:creaturesInRoom) {
            creatureString += c.getName();
            creatureString += " ";
        }

        // Full Room Occupancy String Array
        ArrayList<String> occupancyArray = new ArrayList<String>();
        occupancyArray.add(room.getName());
        occupancyArray.add(" : ");
        occupancyArray.add(characterString);
        occupancyArray.add(" : ");
        occupancyArray.add(creatureString);
        return occupancyArray;
    }


    /**
     * @param level int
     * @param row int
     * @param columns Columns
     *
     * This method adds a row of the Dungeon and its occupancy to the Columns object.
     */
    private void addRowArray (Integer level, Integer row, PrinterColumns columns) {
        ArrayList<Room> rowRooms = new ArrayList<Room>();
        rowRooms.add(dungeon.getRoom("(" + level + "-" + row + "-0)"));
        rowRooms.add(dungeon.getRoom("(" + level + "-" + row + "-1)"));
        rowRooms.add(dungeon.getRoom("(" + level + "-" + row + "-2)"));

        ArrayList<String> rowArray = new ArrayList<String>();
        for (Room r: rowRooms) {
            for (String s: getOccupancyStringArray(r)) {
                rowArray.add(s);
            }
        }
        columns.addLine(rowArray);
    }



    /**
     * @param level int
     * @param columns Columns
     *
     * This method adds a level of the Dungeon and its occupancy to the Columns object.
     */
    private void addLevelArray (Integer level, PrinterColumns columns) {
        for (int r = 0; r <= 2; ++r) {
            addRowArray(level, r, columns);
        }
    }

    /**
     * Shows an overview of game information such as win conditions and entitys
     */
    private void printGameStatus() {
        System.out.print("Game Status: ");
        System.out.print(" Round: ");
        System.out.print(tracker.getRoundCount());
        System.out.print(" Characters: ");
        System.out.print(tracker.getCharacterList().size());
        System.out.print(" Creatures: ");
        System.out.print(tracker.getCreatureList().size());
        System.out.print(" Treasures Collected: ");
        System.out.println(tracker.getTreasureCount());
    }

    /**
     * This method prints Character stats: name, treausres, hp.
     */
    private void printCharacterStats(){
        String tableHeader = new String("Adventurers\tDamage\tTreasure");
        System.out.println(tableHeader);
        for (Character c: tracker.getCharacterList()) {
            String name = c.getName();
            String treasureString = c.getInventoryString();
            Integer damage = 3 - c.getHealth();

            String characterStats = new String(name + "\t\t" + damage + "\t" + treasureString);
            System.out.println(characterStats);
        }
    }


    /**
     * This method prints Creature stats: name and number remaining.
     */
    private void printCreatureStats() {
        ArrayList<String> creatureSet= new ArrayList<String>();
        creatureSet.add("Orbiter");
        creatureSet.add("Seeker");
        creatureSet.add("Blinker");
        String TempString;
        int[] Counts = {0,0,0};
        for (Creature c: tracker.getCreatureList()) {
            Counts[creatureSet.indexOf(c.getName())] += 1;
        }
        System.out.println();
        for (String A: creatureSet) {
            TempString = new String(A + "(s) - " + Counts[creatureSet.indexOf(A)] + " Remaining");
            System.out.println(TempString);
        }
    }


    /**
     * @param character String
     * @param creature String
     * @param characterRoll String
     * @param creatureRoll String
     *
     * Prints the entities and their dice rolls when a Character wins the fight.
     */
    private void printCharacterWins(String character, String creature, String characterRoll, String creatureRoll) {
        System.out.print("Fight: ");
        System.out.print(character + ": ");
        System.out.print(characterRoll);
        System.out.print(" " + creature + ": ");
        System.out.print(creatureRoll);
        System.out.println(" " + character + " Wins :D ");
        System.out.print(character + " celebrates!: ");
        System.out.println();
    }


    /**
     * @param character String
     * @param creature String
     * @param characterRoll String
     * @param creatureRoll String
     *
     * Prints the entities and their dice rolls when a Creature wins the fight.
     */
    private void printCreatureWins(String character, String creature, String characterRoll, String creatureRoll) {
        System.out.print("Fight: ");
        System.out.print(character + ": ");
        System.out.print(characterRoll);
        System.out.print(" " + creature + ": ");
        System.out.print(creatureRoll);
        System.out.println(" Creature Wins :( ");
    }

    
    /**
     * Prints that the fight was skipped.
     */
    private void printFightSkipped() {
        System.out.println("Fight Skipped");
    }


    /**
     * Prints the fight results.
     * 
     * Example of Observer pattern, Printer has subscribed to values from the Tracker 
     * that are updated whenever a fighting event is published.
     */
    public void printFightResults() {
        if (outputType != "ShowNone") {
            HashMap<String, String> fightValues = tracker.getFightValues();
            String result = fightValues.get("result"); // "CharacterWon", "CreatureWon", "FightSkipped"
            if (result == "FightSkipped") {
                // If Fight skipped
                printFightSkipped();
            } else {
                // Fight not skipped
                String character = fightValues.get("character");
                String characterRoll = fightValues.get("characterRoll");
                String creature= fightValues.get("creature");
                String creatureRoll = fightValues.get("creatureRoll");

                if (result == "CharacterWon") {
                    // If Characer Won
                    printCharacterWins(character, creature, characterRoll, creatureRoll);
                } else if (result == "CreatureWon") {
                    // If Creature Won
                    printCreatureWins(character, creature, characterRoll, creatureRoll);
                }
            }
        }
    }

    public void printCelebration(Celebration C1){
        if (outputType != "ShowNone"){
            C1.celebrate();
            System.out.println();}
    }

    /**
     * @param C1 Celebration
     *
     * Print the VCelebration.
     */
    public void printCelebration(Celebration C1){
        if (outputType != "ShowNone"){
            C1.celebrate();
            System.out.println();}
    }

    /**
     * @param treasure String
     * @param score String
     *
     * Prints the treasure and dice roll for a successful treasure hunt.
     */
    private void printTreasureHuntSuccess(String treasure, String score) {
        System.out.print("Treasure Hunt: ");
        System.out.print(score);
        System.out.println(" Success! ");
        System.out.println("Treasure: " + treasure);
    }


    /**
     * @param treasure String
     * @param score String
     *
     * Prints the treasure and dice rolls for a duplicate treasure hunt.
     */
    private void printDuplicateTreasureHunt(String treasure, String score) {
        System.out.print("Treasure Hunt: ");
        System.out.print(score);
        System.out.println(" Success! ");
        System.out.println("Treasure: " + treasure);
        System.out.println(treasure + " Already in Inventory :(");
    }


    /**
     * @param score String
     *
     * Prints the dice roll for an unsuccessful treasure hunt.
     */
    private void printTreasureHuntFail(String score) {
        System.out.print("Treasure Hunt: ");
        System.out.print(score);
        System.out.println(" Fail :(");
    }


    /**
     * Prints the results of treasure hunting.
     *
     * Example of Observer pattern, Printer has subscribed to values from the Tracker 
     * that are updated whenever a treasure hunting event is published.
     */
    public void printTreasureHuntResults() {
        if (outputType != "ShowNone") {
            HashMap<String, String> treasureValues = tracker.getTreasureHuntValues();
            String result = treasureValues.get("result");
            // "TreasureFound", "TreasureNotFound", "DuplicateTreasureFound"
            if (result == "TreasureFound") {
                // If Treasure Found
                printTreasureHuntSuccess(treasureValues.get("treasure"), treasureValues.get("score"));
            } else if (result == "DuplicateTreasureFound") {
                // If Treasure Already Found
                printDuplicateTreasureHunt(treasureValues.get("treasure"), treasureValues.get("score"));
            } else if (result == "TreasureNotFound") {
                // If Treasure Not Found
                printTreasureHuntFail(treasureValues.get("score"));
            }
        }
    }

    public void printStartingScreen(){
        if (outputType != "ShowNone") {
            System.out.println("Starting Game!");
            System.out.println("Press Enter To Continue...");
            scanner.nextLine();
        }  
    }

}
