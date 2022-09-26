public class Shout extends Celebration{
    public Shout(Fight fight){
        //this.celebrationRef = celebrate;
        this.fightRef = fight;
    }

    public int fight(){
        return this.fightRef.fight();
    }

    public void celebrate(){
        this.fightRef.celebrate();
        for(int i = 0; i < DiceRolls.rollDice(3)-1;i++){
            System.out.print("Shout! ");
        }

    }

}
