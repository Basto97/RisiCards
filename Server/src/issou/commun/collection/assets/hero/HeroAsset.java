package issou.commun.collection.assets.hero;

import issou.commun.collection.assets.enums.HeroPowerType;
import issou.commun.collection.assets.enums.HeroType;
import org.json.JSONObject;

import java.util.Objects;

public class HeroAsset implements IHeroAsset {

    private final HeroType type;
    private final HeroPowerType heroPowerType;
    private final int health;
    private final int startMana;

    public HeroAsset(int id, JSONObject json) {
        this.type = HeroType.Get(id);
        this.health  = json.getInt("health");
        this.startMana = json.getInt("startMana");
        this.heroPowerType = HeroPowerType.Get(json.getInt("heroPower"));
    }

    @Override
    public HeroType getType(){
        return type;
    }
    @Override
    public HeroPowerType getHeroPowerType() {
        return heroPowerType;
    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public int getStartMana() {
        return startMana;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroAsset heroAsset = (HeroAsset) o;
        return type == heroAsset.type;
    }
    @Override
    public String toString() {
        return "HeroAsset{" +
                "type=" + type +
                ", health=" + health +
                ", startMana=" + startMana +
                ", heroPowerType=" + heroPowerType +
                '}';
    }
}
