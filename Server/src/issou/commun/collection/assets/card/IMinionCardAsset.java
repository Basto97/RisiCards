package issou.commun.collection.assets.card;

import issou.commun.collection.assets.enums.MinionType;

public interface IMinionCardAsset extends ICardAsset{
    int getHealth();
    int getAttack();
    MinionType getMinionType();
}
