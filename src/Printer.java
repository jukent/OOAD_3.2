import java.util.ArrayList;
import java.util.Scanner;

public class Printer {

    Dungeon dungeon;
    String OutputType; // OneScreen,ShowAll,ShowEnding
    private Scanner A = new java.util.Scanner(System.in);

    /**
     * @param dungeon: Dungeon
     * @param Output: String
     * 
     * Construct the printer
     */
    Printer(Dungeon dungeon,String Output) {
        this.dungeon = dungeon;
        this.OutputType = Output;
    }


    /**
     * Print the Dungeon and its occupancy.
     */
    public void printDungeon() {
        // Level 0 
        Room starting_room = dungeon.getRoom("(0-1-1)");
        ArrayList<String> occupancy_strings = getOccupancyString(starting_room);
        String occupancy_string = occupancy_strings.toString().replace("[", "").replace("]", "");
        System.out.println(occupancy_string);

        // Levels 1, 2, 3, 4
        Columns columns = new Columns();
        for (int l = 1; l <= 4; ++l) {
            printLevel(l, columns);
        }
        columns.print();
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
    private ArrayList<String> getOccupancyString(Room room) {
        // Characters in Room
        ArrayList<Characters> characters_in_room = room.getCharactersInRoom();
        String char_string = new String();
        for (Characters c:characters_in_room) {
            char_string += c.getName();
            char_string += " ";
        }

        // Creatures in Room
        ArrayList<Creatures> creatures_in_room = room.getCreaturesInRoom();
        String creature_string = new String();
        for (Creatures c:creatures_in_room) {
            creature_string += c.getName();
            creature_string += " ";
        }

        // Full Room Occupancy String
        ArrayList<String> occupancy_strings = new ArrayList<String>();
        occupancy_strings.add(room.getName()); 
        occupancy_strings.add(" : "); 
        occupancy_strings.add(char_string); 
        occupancy_strings.add(" : "); 
        occupancy_strings.add(creature_string); 
        //String occupancy_string = new String(room.getName() + ": " + char_string + " : " + creature_string);
        return occupancy_strings;
    }


    /**
     * @param level: int
     * @param row:: int
     * 
     * This method prints a row of the Dungeon and its occupancy.
     */
    private void printRowString (Integer level, Integer row, Columns columns) {
        ArrayList<Room> row_rooms = new ArrayList<Room>();
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-0)"));
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-1)"));
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-2)"));

        ArrayList<String> row_strings = new ArrayList<String>();
        for (Room r: row_rooms) {
            for (String s: getOccupancyString(r)) {
                row_strings.add(s);
            }           
        }
        columns.addLine(row_strings);
    }


    /**
     * @param level: int
     * 
     * This method prints a level of the Dungeon and its occupancy.
     */
    private void printLevel (Integer level, Columns columns) {
        for (int r = 0; r <= 2; ++r) {
            printRowString(level, r, columns);
        }
    }
}
