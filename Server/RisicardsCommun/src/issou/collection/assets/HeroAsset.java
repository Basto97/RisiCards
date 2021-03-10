package issou.collection.assets;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import org.json.JSONObject;

public class HeroAsset {

    private final String name;
    private final String heroPower;
    private final int health;
    private final int startMana;

    public HeroAsset(JSONObject json) {
        this.name = json.getString("name");
        this.health  = json.getInt("health");
        this.startMana = json.getInt("startMana");
        this.heroPower = json.getString("heroPower");
    }

    public String getName() {
        return name;
    }
    public String getHeroPower() {
        return heroPower;
    }
    public int getHealth() {
        return health;
    }
    public int getStartMana() {
        return startMana;
    }
    public ISFSObject toISFS(){
        ISFSObject ret = new SFSObject();
        ret.putUtfString("name", name);
        ret.putUtfString("heroPower", heroPower);
        ret.putInt("health", health);
        ret.putInt("startMana", startMana);
        return ret;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroAsset heroAsset = (HeroAsset) o;
        return name.equals(heroAsset.name);
    }
}
