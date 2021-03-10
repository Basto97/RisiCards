package issou.logic.objects.card;

import issou.collection.assets.MinionCardAsset;

public class MinionCard extends Card{

    private int attack;
    private int health;
    private final String minionType;

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
    public String getMinionType() {
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
