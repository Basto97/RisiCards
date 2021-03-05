package issou.commun.logic.caracters;

import issou.commun.logic.utils.IIdentifiable;

public interface ICharacter extends IIdentifiable {
    void die();
    int getHealth();
    int getHealthMax();
    int getHealthBonus();
    int getAttack();
    int getAttackBonus();
    int getAttackBonusTurn();
    int getAttacksPerTurn();

    void changeHealthBonus(int amount);
    void changeAttackBonus(int amount);
    void changeAttackBonusTurn(int amount);

    void setAttacksPerTurn(int attacksPerTurn);

    int heal(int amount);
    int takeDamages(int amount);

    boolean canAttack();
    int attack();

    void newTurn();
}
