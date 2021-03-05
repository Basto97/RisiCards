package issou.commun.collection.assets.card;

import issou.commun.collection.assets.enums.Types;

public interface ISpellCardAsset extends ICardAsset{
    Types.TargetType getTargetType();
}
