package issou.commun.collection.assets;

import issou.commun.logic.utils.Enums.HeroPowerType;
import org.json.JSONObject;

public class HeroPowerAsset {

    private final HeroPowerType type;
    private final int cost;

    public HeroPowerAsset(JSONObject json){
        this.type = HeroPowerType.valueOf(json.getString("name"));
        this.cost = json.getInt("cost");
    }

    public int getCost(){
        return this.cost;
    }
    public HeroPowerType getHeroPowerType() {
        return this.type;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroPowerAsset that = (HeroPowerAsset) o;
        return type == that.type;
    }
    public String toString() {
        return "HeroPowerAsset{" +
                "type=" + type +
                ", cost=" + cost +
                '}';
    }
}
