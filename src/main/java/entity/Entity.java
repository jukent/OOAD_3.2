package entity;

import dungeon.Dungeon;
import dungeon.Room;
import fight.CreatureFightBehavior;
import fight.FightBehavior;
import movement.MovementBehavior;
import movement.RandomWalkMovement;

public class Entity {
    // Example of abstraction - Characters and Creatures all have their information grouped together
    // This was planned in the initial UML design, and was made abstract
    // because it allows a user to interact with characters without
    // knowing the full details.
    // Entity super class added later for movement behavior

    public int id = 0; // Integer ID value
    protected Dungeon dungeon; // Game Dungeon
    protected Room location; // Room location

    protected String name = new String("Entity"); // Simple Entity name String
    String direction = new String("clockwise"); // Dummy String direction value
    protected int hp = 3; // Health Points Integer

    public FightBehavior fightBehavior = new CreatureFightBehavior(); // FightBehavior
    public MovementBehavior movementBehavior = new RandomWalkMovement(); // MovementBehavior


    /**
     * @return int
     * 
     * This abstract method calls an Entity's fight behavior 
     * and returns their "fight roll" as an integer.
     */
    public int fight() {
        return fightBehavior.fight();
    }
    

    /**
     * Template function for specific move directions
     * to be overwritten by more specific behavior.
     */
    public void move() {
        movementBehavior.move(this, dungeon);
    }


    /**
     * @param room: Room
     * 
     * This method manually sets Entity's location without influence from other Rooms.
     */
    public void setLocation(Room room) {
        this.location = room;
    }


    /**
     * @return Room
     * 
     * This method returns an Entity's location.
     */
    public Room getLocation() {
        return this.location;
    }

    
    /**
     * @param new_direction: String
     * 
     * This method sets an Entity's "direction"
     * 
     * This is only used for Orbiter's, but since we wanted to encapsalate MovementBehavior to work for Creatures or Entity's (so that Characters could blink),
     * All Entity's needed to have any parameters that could be called up by any of the 'move()' methods.
     */
    public void setDirection(String new_direction) {
        this.direction = new_direction;
    }
    

    /**
     * @return String
     * 
     * Make's an entity's movement direction accessible.
     */
    public String getDirection() {
        return this.direction;
    }


    /**
     * @return int
     * 
     * This method returns an Entity's health.
     */
    public int getHealth() {
        return this.hp;
    }


    /**
     * @param n: int
     * 
     * This method decreases an Entity's health by the integer 'n'.
     */
    public void loseHealth(int n) {
        this.hp -= n;
    }


    /**
     * @return String
     * 
     * This method returns an Entity's name.
     */
    public String getName() {
        return this.name;
    }


    /**
     * @return String
     * 
     * This method returns an Entity's type of FightBehavior (i.e. "Creature", "Expert", "Stealthy", "Trained", "Untrained").
     */
    public String getFightType() {
        return this.fightBehavior.getFightType();
    }


    /**
     * @param fightBehavior: FightBehavior
     * 
     * This method sets an Entity's fight behavior.
     */
    public void setFightBehavior(FightBehavior fightBehavior) {
        this.fightBehavior = fightBehavior;
    }


    /**
     * @return FightBehavior
     * 
     * Returns an Entity's FightBehavior.
     */
    public FightBehavior getFightBehavior() {
        return this.fightBehavior;
    }


    /**
     * @return String
     * 
     * This method returns an Entity's MovementType (i.e., "Orbit", "RandomWalk", "Seek", "Blink").
     */
    public String getMovementType() {
        return this.movementBehavior.getMovementType();
    }


    /**
     * @param movement_behavior
     * 
     * This method sets an Entity's MovementBehavior.
     */
    public void setMovementBehavior(MovementBehavior movement_behavior) {
        this.movementBehavior = movement_behavior;
    }
}
