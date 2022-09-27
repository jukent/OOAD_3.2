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
        for (Characters c: tracker.characterList) {
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
  
        for (Creatures c: tracker.creatureList) {
            String name = c.getName();
            String room = c.getLocation().getName();
            String creat_stats = new String(name + "\t\t" + room);
            System.out.println(creat_stats);
        }
    }
}
