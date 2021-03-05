package issou.commun.collection.assets.heropower;

import issou.commun.collection.assets.enums.HeroPowerType;
import org.json.JSONObject;

public class HeroPowerAsset implements IHeroPowerAsset{

    private final HeroPowerType type;
    private final int cost;

    public HeroPowerAsset(int id ,JSONObject json){
        this.type = HeroPowerType.Get(id);
        this.cost = json.getInt("cost");
    }

    @Override
    public int getCost(){
        return this.cost;
    }
    @Override
    public HeroPowerType getHeroPowerType() {
        return this.type;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroPowerAsset that = (HeroPowerAsset) o;
        return type == that.type;
    }
    @Override
    public String toString() {
        return "HeroPowerAsset{" +
                "type=" + type +
                ", cost=" + cost +
                '}';
    }
}
