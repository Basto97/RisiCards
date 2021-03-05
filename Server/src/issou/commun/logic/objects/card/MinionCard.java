package issou.commun.logic.objects.card;

import issou.commun.collection.assets.card.IMinionCardAsset;
import issou.commun.collection.enums.Types.MinionType;

public class MinionCard extends Card implements IMinionCard{

    private int attack;
    private int health;
    private final MinionType minionType;

    public MinionCard(IMinionCardAsset card) {
        super(card);
        this.attack = card.getAttack();
        this.health = card.getHealth();
        this.minionType = card.getMinionType();
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
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
