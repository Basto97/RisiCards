package issou.commun.collection.assets.card;

import issou.commun.collection.assets.enums.Types.MinionType;
import org.json.JSONException;
import org.json.JSONObject;

import static issou.commun.collection.assets.enums.Types.MinionType.*;

public class MinionCardAsset extends CardAsset implements IMinionCardAsset{

    private final int health;
    private final int attack;
    private final MinionType type;

    public MinionCardAsset(int cardID, JSONObject json) {
        super(cardID, json);
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
