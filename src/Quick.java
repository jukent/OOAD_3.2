public class Quick extends TreasureHunt{
    
    public Quick(){
        this.SearchType = "Quick";
        this.NeededScore = 9;
    }

    public int searchTreasure() {
        if (DiceRolls.rollDice(3)==1){return -1;}
            else{return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);}
    }

}
