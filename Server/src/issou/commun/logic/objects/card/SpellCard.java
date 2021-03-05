package issou.commun.logic.objects.card;

import issou.commun.collection.assets.SpellCardAsset;
import issou.commun.logic.utils.Enums.TargetType;

public class SpellCard extends Card{

    private final TargetType targetType;

    public SpellCard(SpellCardAsset card) {
        super(card);
        this.targetType = card.getTargetType();
    }

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
