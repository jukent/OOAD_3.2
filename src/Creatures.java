public abstract class Creatures extends Entities {


    String name = "Creature";
    FightBehavior FightBehavior = new CreatureFighter();

    protected int HP = 1;
    protected int MoveCount = 1;


    /**
     * The abstract method sets a Creature's starting room
     * to be overwritten with more specific Creature behavior.
     */
    protected void setStartingRoom() {}
}
