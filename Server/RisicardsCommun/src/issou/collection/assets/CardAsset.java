package issou.collection.assets;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class CardAsset {

    public final String name;
    public final int cost;
    public final String[] effects;
    public final Integer[] special;
    public final boolean minion;
    public final String hero;

    public final int health;
    public final int attack;
    public final String type;

    public final String targetType;

    public CardAsset(JSONObject json, boolean minion) {
        this.cost = json.getInt("cost");
        this.name = json.getString("name");
        this.hero = json.getString("hero");
        JSONArray temp = json.getJSONArray("effects");
        int length = temp.length();
        effects = new String[length];
        for (int i = 0; i < length; i++)
            effects[i] = temp.getString(i);
        temp = json.getJSONArray("special");
        length = temp.length();
        special = new Integer[length];
        for (int i = 0; i < length; i++)
            special[i] = temp.getInt(i);

        this.minion = minion;
        if(minion){
            this.health =  json.getInt("health");
            this.attack =  json.getInt("attack");
            this.type = json.getString("type");
            this.targetType = "";
        } else {
            this.health =  0;
            this.attack =  0;
            this.type = "";
            this.targetType = json.getString("targetType");
        }
    }

    public ISFSObject toISFS(){
        ISFSObject ret = new SFSObject();
        ret.putUtfString("name", name);
        ret.putInt("cost", cost);
        ret.putIntArray("special", Arrays.asList(special));
        ret.putUtfStringArray("effects", Arrays.asList(effects));
        ret.putBool("minion", minion);
        ret.putInt("health", health);
        ret.putInt("attack", attack);
        ret.putUtfString("hero", hero);
        ret.putUtfString("type", type);
        ret.putUtfString("targetType", targetType);
        return ret;
    }
}
