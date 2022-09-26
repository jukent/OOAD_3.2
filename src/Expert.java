public class Expert extends Fight{
    public Expert(){
        this.FightType = "Expert";}
    
    public int fight(){
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 2;
        }
}
