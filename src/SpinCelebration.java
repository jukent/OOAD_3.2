public class SpinCelebration extends Celebration{


    public SpinCelebration(FightBehavior fight) {
        //this.celebrationRef = celebrate;
        this.fightRef = fight;
    }
    public SpinCelebration(Celebration A) {
        //this.celebrationRef = celebrate;
        this.celebrationRef = A;
        this.fightRef = A.fightRef;
    }

    public int fight() {
        return this.fightRef.fight();
    }

    public void celebrate() {
        this.celebrationRef.celebrate();
        for(int i = 0; i < DiceRolls.rollDice(3)-1;i++){
            System.out.print("Spin! ");
        }

    }

}
