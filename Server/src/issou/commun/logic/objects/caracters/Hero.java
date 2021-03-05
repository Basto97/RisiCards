package issou.commun.logic.objects.caracters;

import issou.commun.collection.assets.HeroAsset;
import issou.commun.logic.utils.Enums.HeroType;

public class Hero extends Character {

    private final HeroType heroType;
    private final int startMana;

    public Hero(HeroAsset heroAsset) {
        super(heroAsset.getHealth(), 0,1 , true);
        this.heroType = heroAsset.getType();
        this.startMana = heroAsset.getStartMana();
    }

    public void die(){

    }

    public HeroType getHeroType() {
        return this.heroType;
    }

    public int getStartMana() {
        return this.startMana;
    }

    public String toString() {
        return "Hero{" + "heroType=" + heroType + '}';
    }
}
