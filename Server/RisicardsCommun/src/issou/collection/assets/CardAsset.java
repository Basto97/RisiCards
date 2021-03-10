package issou.collection.assets;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public abstract class CardAsset {

    private final String name;
    private final int cost;
    private final String[] effects;
    private final String[] special;

    public CardAsset(JSONObject json) {
        this.cost = json.getInt("cost");
        this.name = json.getString("name");
        JSONArray temp = json.getJSONArray("effects");
        int length = temp.length();
        effects = new String[length];
        for (int i = 0; i < length; i++)
            effects[i] = temp.getString(i);
        temp = json.getJSONArray("special");
        length = temp.length();
        special = new String[length];
        for (int i = 0; i < length; i++)
            special[i] = temp.getString(i);
    }

    public String getName(){
        return name;
    }
    public int getCost(){
        return cost;
    }
    public String[] getSpecial(){ return special; }
    public String[] getEffects() {return effects; }

    public ISFSObject toISFS(){
        ISFSObject ret = new SFSObject();
        ret.putUtfString("name", name);
        ret.putInt("cost", cost);
        ret.putUtfStringArray("special", Arrays.asList(special));
        ret.putUtfStringArray("effects", Arrays.asList(effects));
        return ret;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAsset cardAsset = (CardAsset) o;
        return name.equals(cardAsset.getName());
    }
}
