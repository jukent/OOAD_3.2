package main.java;
public class UntrainedFighter extends FightBehavior{
    public UntrainedFighter(){
        this.FightType = "Untrained";}
    
    public int fight(){
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
    
}
