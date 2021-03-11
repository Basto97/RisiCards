package issou.collection.assets;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import org.json.JSONObject;

public class HeroAsset {

    public final String name;
    public final String heroPower;
    public final int health;
    public final int startMana;

    public HeroAsset(JSONObject json) {
        this.name = json.getString("name");
        this.health  = json.getInt("health");
        this.startMana = json.getInt("startMana");
        this.heroPower = json.getString("heroPower");
    }

    public ISFSObject toISFS(){
        ISFSObject ret = new SFSObject();
        ret.putUtfString("name", name);
        ret.putUtfString("heroPower", heroPower);
        ret.putInt("health", health);
        ret.putInt("startMana", startMana);
        return ret;
    }
}
