package issou.commun.collection.assets;

import issou.commun.collection.assets.enums.TargetType;
import org.json.JSONObject;

public class SpellCardAsset extends CardAsset{

    private final TargetType targetType;

    public SpellCardAsset(int id, int manaCost, TargetType targetType) {
        super(id, manaCost);
        this.targetType = targetType;
    }

    public SpellCardAsset(JSONObject json) {
        super(json.getInt("id"),json.getInt("manaCost"));
        this.targetType = TargetType.Get(json.getInt("targetType"));
    }


    @Override
    public String toString() {
        return "SpellCardAsset{" +
                "id=" + id +
                ", manaCost=" + manaCost +
                ", targetType=" + targetType +
                '}';
    }

    @Override
    public CardAsset clone(){
        return new SpellCardAsset(id, manaCost, targetType);
    }
}
