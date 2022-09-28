public class CreatureFighter extends FightBehavior{


    public CreatureFighter() {
        this.FightType = "Creature";}
    
        
    public int fight() {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
    
}
