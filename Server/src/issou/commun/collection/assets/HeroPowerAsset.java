package issou.commun.collection.assets;

import issou.commun.collection.assets.enums.HeroPowerType;
import org.json.JSONObject;

import java.util.Objects;

public class HeroPowerAsset {

    private final HeroPowerType type;
    private final int cost;

    public HeroPowerAsset(HeroPowerType type, int cost) {
        this.type = type;
        this.cost = cost;
    }
    public HeroPowerAsset(int id ,JSONObject json){
        this.type = HeroPowerType.Get(id);
        this.cost = json.getInt("cost");
    }

    public HeroPowerType getType(){
        return type;
    }
    public int getCost(){
        return this.cost;
    }

    @Override
    public String toString() {
        return "HeroPowerAsset{" +
                "type=" + type +
                ", cost=" + cost +
                '}';
    }
    @Override
    public HeroPowerAsset clone(){
        return new HeroPowerAsset(this.type, this.cost);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroPowerAsset that = (HeroPowerAsset) o;
        return type == that.type;
    }
    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
