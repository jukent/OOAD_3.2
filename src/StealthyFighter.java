public class StealthyFighter extends FightBehavior{
    public StealthyFighter(){
        this.FightType = "Stealth";}
    
    public int fight(){
        if (DiceRolls.rollDice(2) == 1) {
            return -1;
        }
        else {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;}}
}

