package issou.commun.logic.objects.card;

import issou.commun.collection.enums.Types;

public interface ISpellCard extends ICard{
    Types.TargetType getTargetType();
}
