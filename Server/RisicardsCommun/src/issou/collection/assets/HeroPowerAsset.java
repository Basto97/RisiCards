package issou.collection.assets;

import org.json.JSONObject;

public class HeroPowerAsset {

    private final String name;
    private final int cost;

    public HeroPowerAsset(JSONObject json){
        this.name = json.getString("name");
        this.cost = json.getInt("cost");
    }

    public int getCost(){
        return this.cost;
    }
    public String getName() {
        return this.name;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroPowerAsset that = (HeroPowerAsset) o;
        return name.equals(that.name);
    }
    public String toString() {
        return "HeroPowerAsset{" +
                "name=" + name +
                ", cost=" + cost +
                '}';
    }
}
