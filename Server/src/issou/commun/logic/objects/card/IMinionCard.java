package issou.commun.logic.objects.card;

import issou.commun.collection.assets.enums.MinionType;

public interface IMinionCard extends ICard {
    int getAttack();
    int getHealth();
    MinionType getMinionType();
}
