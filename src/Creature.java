public class Creature extends FightBehavior{


    public Creature() {
        this.FightType = "Creature";}
    
        
    public int fight() {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
    
}
