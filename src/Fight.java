public class Fight {
        
    protected String FightType;
    protected Celebration Celebrate;


    /**
     * @param Type: String
     * 
     * This method returns the type of Fighter.
     */
    public Fight(String Type) { 
        // Encapsulation example
        // This class hides the behavior fight behavior for each character
        // and creature. Since this varies so much, we encapsulate it in its own
        // class. This is just like the Strategy design pattern. 
        // "Encapsulate what varies"
        FightType = Type;
    }


    /**
     * @return int
     * 
     * This method executes the fight by rolling two dice and adding any extra strength buffs.
     */
    public int fight(){
        if(FightType == "Untrained"){return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);}
        if(FightType == "Creature"){return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);}
        if(FightType == "Trained"){return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;}
        if(FightType == "Expert"){return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 2;}
        if(FightType == "Stealth"){
            if (DiceRolls.rollDice(2) == 1) {
                return -1;
            }
            else {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;}}
        else {return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);}
    }
}
