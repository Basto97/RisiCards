package issou.commun.collection.assets.card;

import issou.commun.collection.enums.Types;

public interface IMinionCardAsset extends ICardAsset{
    int getHealth();
    int getAttack();
    Types.MinionType getMinionType();
}
