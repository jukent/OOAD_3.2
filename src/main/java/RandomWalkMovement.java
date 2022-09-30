package main.java;
import java.util.ArrayList;
import java.util.Random;

public class RandomWalkMovement extends MovementBehavior{
    

    public RandomWalkMovement() {
        this.MovementType = "RandomWalk";
    }


    /* (non-Javadoc)
     * @see MovementBehavior#move()
     * 
     * The template method controlls Entity random movement.
     * 
     */
    @Override
    public void move(Entity entity, Dungeon dungeon) {
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
