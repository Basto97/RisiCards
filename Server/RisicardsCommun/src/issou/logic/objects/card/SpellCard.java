package issou.logic.objects.card;

import issou.collection.assets.SpellCardAsset;

public class SpellCard extends Card{

    private final String targetType;

    public SpellCard(SpellCardAsset card) {
        super(card);
        this.targetType = card.getTargetType();
    }

    public String getTargetType() {
        return targetType;
    }

    @Override
    public String toString() {
        return "SpellCard{" + super.toString()+", "+
                "targetType=" + targetType +
                '}';
    }
}
