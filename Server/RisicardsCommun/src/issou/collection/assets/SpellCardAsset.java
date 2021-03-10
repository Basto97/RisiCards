package issou.collection.assets;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import org.json.JSONObject;

public class SpellCardAsset extends CardAsset {

    private final String targetType;

    public SpellCardAsset(JSONObject json) {
        super(json);
        this.targetType = json.getString("targetType");
    }

    public ISFSObject toISFS(){
        ISFSObject ret = super.toISFS();
        ret.putUtfString("cardType","spell");
        ret.putUtfString("targetType", targetType);
        return ret;
    }

    public String getTargetType() {
        return this.targetType;
    }

}
