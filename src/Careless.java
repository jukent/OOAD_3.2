public class Careless extends TreasureHunt{
    public Careless(){
        this.SearchType = "Careless";
        this.NeededScore = 10;
    }

    public int searchTreasure() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }

    
}
