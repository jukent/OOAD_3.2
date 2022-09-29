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
    private void logCharacterStats(FileWriter fileWriter){
        String tbl_header = new String("Adventurers\tRoom\tDamage\tTreasure");
        try {
            fileWriter.write("\n");
            fileWriter.write(tbl_header);

            fileWriter.write("\n");
            for (Character c: tracker.characterList) {
                String name = c.getName();
                String room = c.getLocation().getName();
                Integer hp = 3-c.getHealth();
                String treasure = c.getInventoryString();

                String char_stats = new String(name + "\t\t" + room + "\t" + hp + "\t\t" + treasure);
                fileWriter.write(char_stats);
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred. Could not write Character stats to file.");
            e.printStackTrace();
        }
    }

    
    /**
     * This method prints Creature stats: name and number remaining.
     */
    private void logCreatureStats(FileWriter fileWriter) {
        try {
            fileWriter.write("\n");
            int total_creat = tracker.creatureList.size();
            fileWriter.write("Total Active Creatures: " + total_creat);
            fileWriter.write("\n");

            fileWriter.write("\n");
            String tbl_header = new String("Creatures\tRoom");
            fileWriter.write(tbl_header);
            fileWriter.write("\n");
    
            for (Creature c: tracker.creatureList) {
                String name = c.getName();
                String room = c.getLocation().getName();
                String creat_stats = new String(name + "\t\t" + room);
                fileWriter.write(creat_stats);
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred. Could not write Creature stats to file.");
            e.printStackTrace();
        }
    }

    public void logRound() {
        int roundCounter = tracker.getRoundCounter();

        String fileName = new String("Logger-files/Logger-" + roundCounter + ".txt");

        try {
            FileWriter fileWriter = new FileWriter(fileName);

            String round_string = new String("Tracker: Turn " + roundCounter);
            fileWriter.write(round_string);
            fileWriter.write("\n");

            logCharacterStats(fileWriter);
            logCreatureStats(fileWriter);

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Could not write file.");
            e.printStackTrace();
        }
    }
}
