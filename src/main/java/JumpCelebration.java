package main.java;
public class JumpCelebration extends Celebration {


    public JumpCelebration(FightBehavior fight) {
        this.fightRef = fight;
    }
    public JumpCelebration(Celebration A) {
        this.celebrationRef = A;
        this.fightRef = A.fightRef;
    }

    public int fight() {
        return this.fightRef.fight();
    }

    public void celebrate() {
        this.celebrationRef.celebrate();
        for(int i = 0; i < DiceRolls.rollDice(3) - 1; i++){
            System.out.print("Jump! ");
        }

    }

}
