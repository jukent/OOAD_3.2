import java.util.ArrayList;
import java.util.Scanner;

public class Printer {

    Dungeon dungeon;
    Tracker tracker;
    String OutputType; // OneScreen,ShowAll,ShowEnding
    private Scanner A = new java.util.Scanner(System.in);

    /**
     * @param dungeon: Dungeon
     * @param Output: String
     * 
     * Construct the printer
     */
    Printer(Dungeon dungeon, Tracker tracker, String Output) {
        this.dungeon = dungeon;
        this.tracker = tracker;
        this.OutputType = Output;
    }


    /**
     * Print the Dungeon and its occupancy.
     */
    public void printDungeon() {
        // Print Game Status
        printGameStatus();

        // Level 0 
        Room starting_room = dungeon.getRoom("(0-1-1)");
        ArrayList<String> occupancy_strings = getOccupancyStringArray(starting_room);
        String occupancy_string = occupancy_strings.toString().replace("[", "").replace("]", "").replace(",", "");
        System.out.println(occupancy_string);

        // Levels 1, 2, 3, 4
        Columns columns = new Columns();
        for (int l = 1; l <= 4; ++l) {
            addLevel(l, columns);
        }
        columns.print();

        // Print Character/Creature Status
        printCharacterStats();
        printCreatureStats();
    }


    /**
     * A pause method between turns asking the player to continue.
     */
    public void pause(){
        if (OutputType == "OneScreen") {
            System.out.println("Press Enter To Continue...");
            A.nextLine();
        }
    }


    /**
     * @param room: Room
     * @return String
     * 
     * This method gets the string for displaying occupancy in each Room.
     */
    private ArrayList<String> getOccupancyStringArray(Room room) {
        // Characters in Room
        ArrayList<Characters> characters_in_room = tracker.getCharactersInRoom(room);
        String char_string = new String();
        for (Characters c:characters_in_room) {
            char_string += c.getName();
            char_string += " ";
        }

        // Creatures in Room
        ArrayList<Creatures> creatures_in_room = tracker.getCreaturesInRoom(room);
        String creature_string = new String();
        for (Creatures c:creatures_in_room) {
            creature_string += c.getName();
            creature_string += " ";
        }

        // Full Room Occupancy String Array
        ArrayList<String> occupancy_strings = new ArrayList<String>();
        occupancy_strings.add(room.getName()); 
        occupancy_strings.add(" : "); 
        occupancy_strings.add(char_string); 
        occupancy_strings.add(" : "); 
        occupancy_strings.add(creature_string); 
        return occupancy_strings;
    }


    /**
     * @param level: int
     * @param row:: int
     * @param columns:: Columns
     * 
     * This method adds a row of the Dungeon and its occupancy to the Columns object.
     */
    private void addRowString (Integer level, Integer row, Columns columns) {
        ArrayList<Room> row_rooms = new ArrayList<Room>();
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-0)"));
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-1)"));
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-2)"));

        ArrayList<String> row_strings = new ArrayList<String>();
        for (Room r: row_rooms) {
            for (String s: getOccupancyStringArray(r)) {
                row_strings.add(s);
            }           
        }
        columns.addLine(row_strings);
    }


    /**
     * @param level: int
     * @param columns:: Columns
     * 
     * This method adds a level of the Dungeon and its occupancy to the Columns object.
     */
    private void addLevel (Integer level, Columns columns) {
        for (int r = 0; r <= 2; ++r) {
            addRowString(level, r, columns);
        }
    }

    /**
     * Shows an overview of game information such as win conditions and entitys
     */
    private void printGameStatus() {
        System.out.print("Game Status: ");
        System.out.print(" Round: ");
        System.out.print(tracker.roundCounter);
        System.out.print(" Characters: ");
        System.out.print(tracker.characterList.size());
        System.out.print(" Creatures: ");
        System.out.print(tracker.creatureList.size());
        System.out.print(" Treasures Collected: ");
        System.out.println(tracker.treasureCount);
    }

        /**
     * This method prints Character stats: name, treausres, hp.
     */
    private void printCharacterStats(){

        String tbl_header = new String("Adventurers\tDamage\tTreasure");
        System.out.println(tbl_header);
        for (Characters c: tracker.characterList) {
            String name = c.getName();
            String treasure_str = c.getInventoryString();
            Integer hp = 3-c.getHealth();

            String char_stats = new String(name + "\t\t" + hp + "\t" + treasure_str);
            System.out.println(char_stats);
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
        for (Creatures c: tracker.creatureList) {
            Counts[creatureSet.indexOf(c.getName())] += 1;
        }
        System.out.println();
        for (String A: creatureSet) {
            TempString = new String(A + " - " + Counts[creatureSet.indexOf(A)] + " Remaining");
            System.out.println(TempString);
        }
    }
}
