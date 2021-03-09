package issou.collection.assets;

import issou.logic.utils.Enums.TargetType;
import org.json.JSONObject;

public class SpellCardAsset extends CardAsset {

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
