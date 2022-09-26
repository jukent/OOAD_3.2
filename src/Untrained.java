public class Untrained extends Fight{
    public Untrained(){
        this.FightType = "Untrained";}
    
    public int fight(){
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
    
}
