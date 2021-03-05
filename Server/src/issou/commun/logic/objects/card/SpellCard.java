package issou.commun.logic.objects.card;

import issou.commun.collection.assets.card.ISpellCardAsset;
import issou.commun.collection.assets.enums.Types.TargetType;

public class SpellCard extends Card implements ISpellCard{

    private final TargetType targetType;

    public SpellCard(ISpellCardAsset card) {
        super(card);
        this.targetType = card.getTargetType();
    }

    @Override
    public TargetType getTargetType() {
        return targetType;
    }

    @Override
    public String toString() {
        return "SpellCard{" + super.toString()+", "+
                "targetType=" + targetType +
                '}';
    }
}
