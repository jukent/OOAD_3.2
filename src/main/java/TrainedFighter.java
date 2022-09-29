package main.java;
public class TrainedFighter extends FightBehavior{

    public TrainedFighter(){
    this.FightType = "Trained";}

    public int fight(){
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;
    }
}
