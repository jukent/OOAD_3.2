package main.java;
public class Entity {

    public int ID = 0;
    // Example of abstraction - Characters and Creatures all have their information grouped together
    // This was planned in the initial UML design, and was made abstract
    // because it allows a user to interact with characters without
    // knowing the full details.
    // Entity super class added later for movement behavior

    // Room location stored Room object accessed by name (level-row-column)
    // Level Range [0-4], Column range [1-3], Row Range [1-3]
    Dungeon dungeon;
    protected Room Location;

    String name = new String("Entity");
    String direction = new String("clockwise");
    int HP = 3;

    public FightBehavior FightBehavior = new CreatureFighter();
    public MovementBehavior MovementBehavior = new RandomWalkMovement();


    /**
     * @return int
     * 
     * This abstract method calls an Entity's fight behavior 
     * and returns their "fight roll" as an integer.
     */
    public int fight() {
        return FightBehavior.fight();
    }
    

    /**
     * Template function for specific move directions
     * to be overwritten by more specific behavior.
     */
    public void move() {
        MovementBehavior.move(this, dungeon);
    }


    /**
     * @param room: Room
     * 
     * This method manually sets Entity's location without influence from other Rooms.
     */
    public void setLocation(Room room) {
        this.Location = room;
    }


    /**
     * @return Room
     * 
     * This method returns an Entity's location.
     */
    public Room getLocation() {
        return this.Location;
    }

    public void setDirection(String new_direction) {
        this.direction = new_direction;
    }
    
    public String getDirection() {
        return this.direction;
    }


    /**
     * @return int
     * 
     * This method returns an Entity's health.
     */
    public int getHealth() {
        return this.HP;
    }


    /**
     * @param n: int
     * 
     * This method decreases an Entity's health by the integer 'n'.
     */
    public void loseHealth(int n) {
        this.HP = this.HP - n;
    }


    /**
     * @return String
     * 
     * This method returns an Entity's name.
     */
    public String getName() {
        return this.name;
    }

    public String getMovementType() {
        return this.MovementBehavior.MovementType;
    }
}
