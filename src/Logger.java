//import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;

// UPDATE TO USE LOGGING API -- https://www.vogella.com/tutorials/Logging/article.html

public class Logger {
    // to be instantiated at the beginning of each full adventurer/creature turn (not individual) and closes at the end of each turn
    // print results to "Logger-n.txt" where n is the turn of the simulation

    Tracker tracker;

    public Logger(Tracker tracker) {
        this.tracker = tracker;
    }

    /**
     * This method prints Character stats: name, treausres, hp.
     */
    private void printCharacterStats(){
        String tbl_header = new String("Adventurers\tRoom\tDamage\tTreasure");
        System.out.println(tbl_header);
        for (Character c: tracker.characterList) {
            String name = c.getName();
            String room = c.getLocation().getName();
            Integer hp = 3-c.getHealth();
            String treasure = c.getInventoryString();

            String char_stats = new String(name + "\t\t" + room + "\t" + hp + "\t" + treasure);
            System.out.println(char_stats);
        }
    }

    
    /**
     * This method prints Creature stats: name and number remaining.
     */
    private void printCreatureStats() {
        System.out.println("\n");
        int total_creat = tracker.creatureList.size();
        System.out.println("Total Active Creatures: " + total_creat);


        System.out.println("\n");
        String tbl_header = new String("Creatures\tRoom");
        System.out.println(tbl_header);
  
        for (Creature c: tracker.creatureList) {
            String name = c.getName();
            String room = c.getLocation().getName();
            String creat_stats = new String(name + "\t\t" + room);
            System.out.println(creat_stats);
        }
    }

    public void printLog() {
        int roundCounter = tracker.getRoundCounter();

        String fileName = new String("Logger-" + roundCounter + ".txt");

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Could not write file.");
            e.printStackTrace();
        }

        System.out.println("Tracker: Turn " + roundCounter);

        printCharacterStats();
        printCreatureStats();
    }
}
