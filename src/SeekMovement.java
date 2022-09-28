import java.util.ArrayList;
import java.util.Random;

public class SeekMovement extends MovementBehavior{


    public SeekMovement() {
        this.MovementType = "Seek";
    }


    /* (non-Javadoc)
     * @see MovementBehavior#move()
     * 
     * Seekers move by staying still and waiting for a Character to be in a nearby Room
     * Then they move to the room with the Character.
     */
    @Override
    public void move(Entities entity, Dungeon dungeon) {
        Room current_room = entity.getLocation();

        // List of nearby rooms
        ArrayList<String>exits = current_room.getExits();
        
        // Populate an ArrayList of populated nearby rooms
        ArrayList<Room> populated_exits = new ArrayList<>();
        for (String x: exits) {
            // Convert Exit Room-Name Strings to Rooms 
            Room exit_room = dungeon.getRoom(x);
            // Check if a Character is in the Exit Room
            ArrayList<Characters> characters_in_room = exit_room.getCharactersInRoom();
            if (characters_in_room.size() > 0) {
                // If character in room add it to possible exit_rooms
                populated_exits.add(exit_room);
            }
        }

        // Move based on interesections
        if (populated_exits.size() == 0 ) {
            // If no intersection, don't move
            entity.setLocation(entity.getLocation());
        } else if (populated_exits.size() == 1) {
            // If one intersection, move there
            Room new_room = populated_exits.get(0);
            entity.setLocation(new_room);
        } else {
            // If multiple intersections, choose one randomly
            Random random = new Random();
            int random_index = random.nextInt(populated_exits.size());
        
            Room new_room = populated_exits.get(random_index);
            entity.setLocation(new_room);
        } 
    }
}
