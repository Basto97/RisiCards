package issou.commun.collection.assets;

import issou.commun.collection.assets.enums.HeroType;
import issou.commun.collection.assets.enums.HeroPowerType;
import org.json.JSONObject;

import java.util.Objects;

public class HeroAsset {

    private final HeroType type;
    private final HeroPowerType heroPowerType;
    private final int health;
    private final int startMana;

    public HeroAsset(HeroType type, int health, int startMana, HeroPowerType heroPowerType) {
        this.type = type;
        this.health = health;
        this.startMana = startMana;
        this.heroPowerType = heroPowerType;
    }
    public HeroAsset(int id, JSONObject json) {
        this.type = HeroType.Get(id);
        this.health  = json.getInt("health");
        this.startMana = json.getInt("startMana");
        this.heroPowerType = HeroPowerType.Get(json.getInt("heroPower"));
    }

    public HeroType getType(){
        return type;
    }
    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "CharacterAsset{" +
                "type=" + type +
                ", health=" + health +
                ", startMana=" + startMana +
                ", heroPowerType=" + heroPowerType +
                '}';
    }
    @Override
    public HeroAsset clone() {
        return new HeroAsset(type, health, startMana, heroPowerType);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroAsset that = (HeroAsset) o;
        return type == that.type;
    }
    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
