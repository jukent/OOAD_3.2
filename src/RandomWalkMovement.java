import java.util.ArrayList;
import java.util.Random;

public class RandomWalkMovement extends MovementBehavior{
    

    public RandomWalkMovement() {
        this.MovementType = "RandomWalk";
    }


    /* (non-Javadoc)
     * @see MovementBehavior#move()
     * 
     * The template method controlls Character random movement.
     * 
     * // Perhaps pass in currentLocation instead?? So that these movement patterns aren't tied to a Character or a Creature Class
     */
    @Override
    public void move(Entities entity, Dungeon dungeon) {
        // Find ArrayList of current room's exits
        Room current_room = entity.getLocation();
        ArrayList<String>exits = current_room.getExits();

        // Select one of the exit rooms by random number generation
        Random random = new Random();
        int random_index = random.nextInt(exits.size());

        // Find the room associated with the random index
        String new_room_name = exits.get(random_index);
        Room new_room = dungeon.getRoom(new_room_name);
    
        // Move there
        entity.setLocation(new_room);
    }
}
