package issou.logic.caracters;

public abstract class CharacterAttacker extends Character{

    private final int attack;
    private int bonusAttack;
    private int bonusAttackThisTurn;

    private int attacksForOneTurn;
    private int attacksLeftThisTurn;

    public CharacterAttacker(int maxHealth, int attack, int attacksForOneTurn, boolean charge){
        super(maxHealth);
        this.attack = attack;
        this.attacksForOneTurn = attacksForOneTurn;
        if(charge)
            attacksLeftThisTurn = this.attacksForOneTurn;
    }

    public void increaseAttack(int amount){
        bonusAttack += amount;
    }

    public void increaseAtackForOneTurn(int amount){
        bonusAttackThisTurn += amount;
    }

    public int currentAttack(){
        return attack+bonusAttack+bonusAttackThisTurn;
    }
}
