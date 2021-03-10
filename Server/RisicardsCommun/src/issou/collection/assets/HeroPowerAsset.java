package issou.collection.assets;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import org.json.JSONObject;

public class HeroPowerAsset{

    private final String name;
    private final int cost;

    public HeroPowerAsset(JSONObject json){
        this.name = json.getString("name");
        this.cost = json.getInt("cost");
    }

    public int getCost(){
        return this.cost;
    }
    public String getName() {
        return this.name;
    }

    public ISFSObject toISFS(){
        ISFSObject ret = new SFSObject();
        ret.putUtfString("name", name);
        ret.putInt("cost", cost);
        return ret;
    }

    public boolean equals(HeroPowerAsset o) {
        return name.equals(o.name);
    }
}
