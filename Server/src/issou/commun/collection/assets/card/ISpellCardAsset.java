package issou.commun.collection.assets.card;

import issou.commun.collection.enums.Types;

public interface ISpellCardAsset extends ICardAsset{
    Types.TargetType getTargetType();
}
