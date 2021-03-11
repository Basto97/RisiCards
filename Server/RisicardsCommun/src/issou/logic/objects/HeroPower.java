package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import issou.collection.assets.HeroPowerAsset;

public class HeroPower {

    private String name;
    private int cost;
    private int costModification;

    public HeroPower(HeroPowerAsset heroPowerAsset) {
        this.cost = heroPowerAsset.getCost();
        this.name = heroPowerAsset.getName();
        this.costModification = 0;
    }

    public int getCostModification(){
        return this.costModification;
    }
    public int getCost() {
        return this.cost;
    }
    public String getName() {
        return name;
    }

    public ISFSObject toSFSObject() {
        ISFSObject obj = new SFSObject();
        obj.putUtfString("name", name);
        obj.putInt("cost", cost);
        obj.putInt("costModification", costModification);
        return obj;
    }
}
