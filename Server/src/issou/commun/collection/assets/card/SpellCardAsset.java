package issou.commun.collection.assets.card;

import issou.commun.collection.assets.enums.TargetType;
import org.json.JSONObject;

public class SpellCardAsset extends CardAsset implements ISpellCardAsset{

    private final TargetType targetType;

    public SpellCardAsset(int cardID, JSONObject json) {
        super(cardID, json);
        this.targetType = TargetType.Get(json.getInt("targetType"));
    }

    @Override
    public TargetType getTargetType() {
        return this.targetType;
    }
    @Override
    public String toString() {
        return "SpellCardAsset{" + super.toString() + ", manaCost=" + manaCost + ", targetType=" + targetType + '}';
    }
}
