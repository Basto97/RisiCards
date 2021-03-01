package issou.commun.collection.assets;

import issou.commun.collection.assets.enums.MinionType;
import org.json.JSONObject;

public class MinionCardAsset extends CardAsset{

    private final int health;
    private final int attack;
    private final MinionType type;

    public MinionCardAsset(int id, int manaCost, int health, int attack, MinionType type) {
        super(id, manaCost);
        this.health = health;
        this.attack = attack;
        this.type = type;
    }

    public MinionCardAsset(JSONObject json) {
        super(json.getInt("id"),json.getInt("manaCost"));
        this.health =  json.getInt("health");
        this.attack =  json.getInt("attack");
        this.type = MinionType.Get(json.getInt("type"));
    }

    @Override
    public String toString() {
        return "MinionCardAsset{" +
                "id=" + id +
                ", manaCost=" + manaCost +
                ", health=" + health +
                ", attack=" + attack +
                ", type=" + type +
                '}';
    }

    @Override
    public CardAsset clone(){
        return new MinionCardAsset(id, manaCost, health, attack, type);
    }
}
