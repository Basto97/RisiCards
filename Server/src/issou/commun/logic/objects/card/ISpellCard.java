package issou.commun.logic.objects.card;

import issou.commun.collection.assets.enums.Types;

public interface ISpellCard extends ICard{
    Types.TargetType getTargetType();
}
