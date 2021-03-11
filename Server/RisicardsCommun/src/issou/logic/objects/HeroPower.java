package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import issou.collection.assets.HeroPowerAsset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeroPower {

    public String name;
    public int cost;
    public int costModification;
    public List<Integer> special;
    public String targetType;

    public HeroPower(HeroPowerAsset heroPowerAsset) {
        this.cost = heroPowerAsset.cost;
        this.name = heroPowerAsset.name;
        this.costModification = 0;
        this.targetType = heroPowerAsset.targetType;
        this.special = new ArrayList<>(Arrays.asList(heroPowerAsset.special));
    }

    public ISFSObject toSFSObject() {
        ISFSObject obj = new SFSObject();
        obj.putUtfString("name", name);
        obj.putInt("cost", cost);
        obj.putInt("costModification", costModification);
        obj.putUtfString("targetType", targetType);
        obj.putIntArray("special", special);
        return obj;
    }
}
