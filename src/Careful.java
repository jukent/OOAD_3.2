public class Careful extends TreasureHunt{
    
    public Careful(){
        this.SearchType = "Careful";
        this.NeededScore = 7;
    }

    public int searchTreasure() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }


}
