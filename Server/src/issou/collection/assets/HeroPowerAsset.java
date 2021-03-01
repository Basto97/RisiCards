package issou.collection.assets;

import issou.collection.assets.enums.HeroPowerType;
import org.json.JSONObject;

public class HeroPowerAsset {

    private final HeroPowerType type;
    private final int cost;

    public HeroPowerAsset(HeroPowerType type, int cost) {
        this.type = type;
        this.cost = cost;
    }
    public HeroPowerAsset(JSONObject json){
        this.type = HeroPowerType.Get(json.getInt("id"));
        this.cost = json.getInt("cost");
    }

    public int getCost(){
        return this.cost;
    }

    public boolean equals(HeroPowerAsset o) {
        return type == o.type;
    }

    @Override
    public HeroPowerAsset clone(){
        return new HeroPowerAsset(this.type, this.cost);
    }
}
