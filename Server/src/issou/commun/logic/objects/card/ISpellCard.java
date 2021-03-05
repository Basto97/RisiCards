package issou.commun.logic.objects.card;

import issou.commun.collection.assets.enums.TargetType;

public interface ISpellCard extends ICard{
    TargetType getTargetType();
}
