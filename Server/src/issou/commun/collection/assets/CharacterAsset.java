package issou.commun.collection.assets;

import issou.commun.collection.assets.enums.CharacterType;
import issou.commun.collection.assets.enums.HeroPowerType;
import org.json.JSONObject;

import java.util.Objects;

public class CharacterAsset {

    private final CharacterType type;
    private final int health;
    private final int startMana;
    private final HeroPowerType heroPowerType;

    public CharacterAsset(CharacterType type, int health, int startMana, HeroPowerType heroPowerType) {
        this.type = type;
        this.health = health;
        this.startMana = startMana;
        this.heroPowerType = heroPowerType;
    }
    public CharacterAsset(int id, JSONObject json) {
        this.type = CharacterType.Get(id);
        this.health  = json.getInt("health");
        this.startMana = json.getInt("startMana");
        this.heroPowerType = HeroPowerType.Get(json.getInt("heroPower"));
    }

    public CharacterType getType(){
        return type;
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
    public CharacterAsset clone() {
        return new CharacterAsset(type, health, startMana, heroPowerType);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterAsset that = (CharacterAsset) o;
        return type == that.type;
    }
    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
