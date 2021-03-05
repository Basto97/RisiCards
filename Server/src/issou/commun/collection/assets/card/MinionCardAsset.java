package issou.commun.collection.assets.card;

import issou.commun.collection.assets.enums.MinionType;
import org.json.JSONObject;

public class MinionCardAsset extends CardAsset implements IMinionCardAsset{

    private final int health;
    private final int attack;
    private final MinionType type;

    public MinionCardAsset(int cardID, JSONObject json) {
        super(cardID, json);
        this.health =  json.getInt("health");
        this.attack =  json.getInt("attack");
        this.type = MinionType.Get(json.getInt("type"));
    }

    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public int getAttack() {
        return attack;
    }
    @Override
    public MinionType getMinionType() {
        return type;
    }
    @Override
    public String toString() {
        return "MinionCardAsset{" + super.toString() + ", health=" + health + ", attack=" + attack + ", type=" + type + '}';
    }
}
