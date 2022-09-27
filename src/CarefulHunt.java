public class CarefulHunt extends TreasureHunt{
    
    public CarefulHunt(){
        this.SearchType = "Careful";
        this.NeededScore = 7;
    }

    public int searchTreasure() {
        return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
    }


}
