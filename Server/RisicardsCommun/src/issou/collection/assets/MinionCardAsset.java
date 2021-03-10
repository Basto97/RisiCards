package issou.collection.assets;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class MinionCardAsset extends CardAsset {

    private final int health;
    private final int attack;
    private final String type;

    public MinionCardAsset(JSONObject json) {
        super(json);
        this.health =  json.getInt("health");
        this.attack =  json.getInt("attack");
        this.type = json.getString("type");
    }

    public int getHealth() {
        return health;
    }
    public int getAttack() {
        return attack;
    }
    public String getMinionType() {
        return type;
    }

    public ISFSObject toISFS(){
        ISFSObject ret = super.toISFS();
        ret.putUtfString("cardType","minion");
        ret.putInt("health", health);
        ret.putInt("attack", attack);
        ret.putUtfString("type", type);
        return ret;
    }
    public String toString() {
        return "MinionCardAsset{" + super.toString() + ", health=" + health + ", attack=" + attack + ", type=" + type + '}';
    }
}
