package issou.collection.assets;

import issou.collection.assets.enums.CharacterType;
import issou.collection.assets.enums.HeroPowerType;
import org.json.JSONObject;

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
    public CharacterAsset(JSONObject json) {
        this.type = CharacterType.Get(json.getInt("id"));
        this.health  = json.getInt("health");
        this.startMana = json.getInt("startMana");
        this.heroPowerType = HeroPowerType.Get(json.getInt("heroPower"));
    }

    public boolean equals(CharacterAsset o) {
        return type == o.type;
    }

    @Override
    public CharacterAsset clone() {
        return new CharacterAsset(type, health, startMana, heroPowerType);
    }
}
