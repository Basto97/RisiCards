package issou.commun.collection.assets;

import issou.commun.logic.utils.Enums.MinionType;
import org.json.JSONException;
import org.json.JSONObject;

import static issou.commun.logic.utils.Enums.MinionType.*;

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
            return valueOf(type);
        } catch (JSONException ignored){
            return SansType;
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
