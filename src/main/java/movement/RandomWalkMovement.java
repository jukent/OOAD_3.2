package movement;

import java.util.ArrayList;
import java.util.Random;

import dungeon.Dungeon;
import dungeon.Room;
import entity.Entity;

public class RandomWalkMovement extends MovementBehavior{
    

    /**
     * Constructor for Random Walking Movement.
     */
    public RandomWalkMovement() {
        this.setMovementType("RandomWalk");
    }


    /* (non-Javadoc)
     * @see movement.MovementBehavior#move(entity.Entity, dungeon.Dungeon)
     * 
     * The template method controlls Entity random movement.
     * 
     */
    @Override
    public void move(Entity entity, Dungeon dungeon) {
        // Find ArrayList of current room's exits
        Room currentRoom = entity.getLocation();
        ArrayList<String> exits = currentRoom.getExits();

        // Select one of the exit rooms by random number generation
        Random random = new Random();
        int i = random.nextInt(exits.size());

        // Find the room associated with the random index
        String newRoomName = exits.get(i);
        Room newRoom = dungeon.getRoom(newRoomName);
    
        // Move there
        entity.setLocation(newRoom);
    }
}
