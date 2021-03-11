package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.collection.assets.HeroAsset;

public class Hero extends Character implements SerializableSFSType {

    public final String name;

    public Hero(HeroAsset heroAsset) {
        super(heroAsset.health, 0,1 , true);
        this.name = heroAsset.name;
    }

    public SFSObject toSFSObject(){
        SFSObject obj = super.toSFSObject();
        obj.putUtfString("name", name);
        return obj;
    }
}
