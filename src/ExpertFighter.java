public class ExpertFighter extends FightBehavior{


    public ExpertFighter(){
        this.FightType = "Expert";}
    

    public int fight(){
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 2;
        }
}
