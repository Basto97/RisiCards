package issou.commun.logic.objects.card;

import issou.commun.collection.assets.MinionCardAsset;
import issou.commun.logic.utils.Enums.MinionType;

public class MinionCard extends Card{

    private int attack;
    private int health;
    private final MinionType minionType;

    public MinionCard(MinionCardAsset card) {
        super(card);
        this.attack = card.getAttack();
        this.health = card.getHealth();
        this.minionType = card.getMinionType();
    }

    public int getAttack() {
        return attack;
    }
    public int getHealth() {
        return health;
    }
    public MinionType getMinionType() {
        return minionType;
    }

    @Override
    public String toString() {
        return "MinionCard{" +super.toString()+","+
                "attack=" + attack +
                ", health=" + health +
                ", minionType=" + minionType +
                '}';
    }
}
