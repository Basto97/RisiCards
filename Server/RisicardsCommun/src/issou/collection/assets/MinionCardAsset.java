package issou.collection.assets;

import issou.logic.utils.Enums.MinionType;
import org.json.JSONException;
import org.json.JSONObject;

public class MinionCardAsset extends CardAsset {

    private final int health;
    private final int attack;
    private final MinionType type;

    public MinionCardAsset(JSONObject json) {
        super(json);
        this.health =  json.getInt("health");
        this.attack =  json.getInt("attack");
        this.type = setType(json);
    }
    private MinionType setType(JSONObject json){
        try {
            String type = json.getString("type");
            return MinionType.valueOf(type);
        } catch (JSONException ignored){
            return MinionType.SansType;
        }
    }

    public int getHealth() {
        return health;
    }
    public int getAttack() {
        return attack;
    }
    public MinionType getMinionType() {
        return type;
    }
    public String toString() {
        return "MinionCardAsset{" + super.toString() + ", health=" + health + ", attack=" + attack + ", type=" + type + '}';
    }
}
