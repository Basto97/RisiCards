package issou.collection.assets;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class HeroPowerAsset{

    public final String name;
    public final int cost;
    public final Integer[] special;

    public final String targetType;

    public HeroPowerAsset(JSONObject json){
        this.name = json.getString("name");
        this.cost = json.getInt("cost");
        JSONArray temp = json.getJSONArray("special");
        int length = temp.length();
        special = new Integer[length];
        for (int i = 0; i < length; i++)
            special[i] = temp.getInt(i);
        this.targetType = json.getString("targetType");
    }

    public ISFSObject toISFS(){
        ISFSObject ret = new SFSObject();
        ret.putUtfString("name", name);
        ret.putInt("cost", cost);
        ret.putIntArray("special", Arrays.asList(special));
        ret.putUtfString("targetType", targetType);
        return ret;
    }

}
