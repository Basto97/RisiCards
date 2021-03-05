package issou.commun.logic.objects.heropower;

import issou.commun.collection.assets.enums.Types.HeroPowerType;
import issou.commun.collection.assets.heropower.IHeroPowerAsset;

public class HeroPower implements IHeroPower{

    private int cost;
    private HeroPowerType heroPowerType;

    public HeroPower(IHeroPowerAsset heroPowerAsset) {
        this.cost = heroPowerAsset.getCost();
        this.heroPowerType = heroPowerAsset.getHeroPowerType();
    }

    @Override
    public int getCost() {
        return this.cost;
    }
    @Override
    public HeroPowerType getHeroPowerType() {
        return this.heroPowerType;
    }

    @Override
    public String toString() {
        return "HeroPower{" +
                "cost=" + cost +
                ", heroPowerType=" + heroPowerType +
                '}';
    }
}
