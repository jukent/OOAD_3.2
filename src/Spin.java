public class Spin extends Celebration{
    public Spin(Fight fight){
        this.FightBehavior = fight;
    }

    public int fight(){
        return this.FightBehavior.fight();
    }

    public void celebrate(){
        for(int i = 0; i < DiceRolls.rollDice(3)-1;i++){
            System.out.print("Spin! ");
        }

    }

}
