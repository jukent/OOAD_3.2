public class Trained extends Fight{

    public Trained(){
    this.FightType = "Trained";}

    public int fight(){
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;
    }
}
