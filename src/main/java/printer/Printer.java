package printer;

import java.util.ArrayList;
import java.util.Scanner;

import dungeon.Dungeon;
import dungeon.Room;
import entity.character.Character;
import entity.creature.Creature;
import track.Tracker;

public class Printer {

    Dungeon dungeon; // Game Dungeon
    Tracker tracker; // Game Tracker
    String OutputType; // Output options: OneScreen, ShowAll, ShowEnding
    private Scanner scanner = new java.util.Scanner(System.in); // A Scanner for awaiting user input

    
    /**
     * @param dungeon: Dungeon
     * @param output: String
     * @param tracker: Tracker
     * 
     * Construct the printer.
     */
    public Printer(Dungeon dungeon, Tracker tracker, String output) {
        this.dungeon = dungeon; // Game Dungeon
        this.tracker = tracker; // Game Tracker
        this.OutputType = output; // Output type String
    }


    /**
     * Print the Dungeon and its occupancy.
     */
    public void printDungeon() {
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


    /**
     * A pause method between turns asking the player to continue.
     */
    public void pause() {
        if (OutputType == "OneScreen") {
            System.out.println("Press Enter To Continue...");
            scanner.nextLine();
        }
    }


    /**
     * @param room: Room
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
     * @param level: int
     * @param row:: int
     * @param columns:: Columns
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
     * @param level: int
     * @param columns:: Columns
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
        System.out.print(tracker.getRoundCounter());
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
}
