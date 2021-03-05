package issou.commun.collection.assets.card;

import issou.commun.collection.enums.Types.TargetType;
import org.json.JSONObject;

public class SpellCardAsset extends CardAsset implements ISpellCardAsset{

    private final TargetType targetType;

    public SpellCardAsset(JSONObject json) {
        super(json);
        this.targetType = TargetType.valueOf(json.getString("targetType"));
    }

    public TargetType getTargetType() {
        return this.targetType;
    }
    public String toString() {
        return "SpellCardAsset{" + super.toString() + ", manaCost=" + getManaCost() + ", targetType=" + targetType + '}';
    }
}
