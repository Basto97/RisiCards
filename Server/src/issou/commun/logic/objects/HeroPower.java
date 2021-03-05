package issou.commun.logic.objects;

import issou.commun.collection.assets.HeroPowerAsset;
import issou.commun.logic.utils.Enums.HeroPowerType;

public class HeroPower {

    private int cost;
    private HeroPowerType heroPowerType;

    public HeroPower(HeroPowerAsset heroPowerAsset) {
        this.cost = heroPowerAsset.getCost();
        this.heroPowerType = heroPowerAsset.getHeroPowerType();
    }

    public int getCost() {
        return this.cost;
    }
    public HeroPowerType getHeroPowerType() {
        return this.heroPowerType;
    }

    public String toString() {
        return "HeroPower{" +
                "cost=" + cost +
                ", heroPowerType=" + heroPowerType +
                '}';
    }
}
