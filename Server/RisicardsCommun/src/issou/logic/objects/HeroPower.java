package issou.logic.objects;

import issou.collection.assets.HeroPowerAsset;

public class HeroPower {

    private String name;
    private int cost;

    public HeroPower(HeroPowerAsset heroPowerAsset) {
        this.cost = heroPowerAsset.getCost();
        this.name = heroPowerAsset.getName();
    }

    public int getCost() {
        return this.cost;
    }
    public String getName() {
        return name;
    }

    public String toString() {
        return "HeroPower{" +
                "cost=" + cost +
                ", heroPowerType=" + name +
                '}';
    }
}
