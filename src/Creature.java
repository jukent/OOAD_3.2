public class Creature extends Fight{
    public Creature(){
        this.FightType = "Creature";}
    
    public int fight(){
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
    
}
