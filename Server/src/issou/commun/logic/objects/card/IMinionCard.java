package issou.commun.logic.objects.card;


import issou.commun.collection.enums.Types;

public interface IMinionCard extends ICard {
    int getAttack();
    int getHealth();
    Types.MinionType getMinionType();
}
