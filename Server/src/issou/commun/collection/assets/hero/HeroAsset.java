package issou.commun.collection.assets.hero;

import issou.commun.collection.enums.Types.HeroPowerType;
import issou.commun.collection.enums.Types.HeroType;
import org.json.JSONObject;

public class HeroAsset implements IHeroAsset {

    private final HeroType type;
    private final HeroPowerType heroPowerType;
    private final int health;
    private final int startMana;

    public HeroAsset(JSONObject json) {
        this.type = HeroType.valueOf(json.getString("name"));
        this.health  = json.getInt("health");
        this.startMana = json.getInt("startMana");
        this.heroPowerType = HeroPowerType.valueOf(json.getString("heroPower"));
    }

    public HeroType getType(){
        return type;
    }
    public HeroPowerType getHeroPowerType() {
        return heroPowerType;
    }
    public int getHealth() {
        return health;
    }
    public int getStartMana() {
        return startMana;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroAsset heroAsset = (HeroAsset) o;
        return type == heroAsset.type;
    }
    public String toString() {
        return "HeroAsset{" +
                "type=" + type +
                ", health=" + health +
                ", startMana=" + startMana +
                ", heroPowerType=" + heroPowerType +
                '}';
    }
}
