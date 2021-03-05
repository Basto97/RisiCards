package issou.commun.logic.caracters.hero;

import issou.commun.collection.enums.Types.HeroType;
import issou.commun.collection.assets.hero.IHeroAsset;
import issou.commun.logic.caracters.Character;

public class Hero extends Character implements IHero {

    private final HeroType heroType;
    private final int startMana;

    public Hero(IHeroAsset heroAsset) {
        super(heroAsset.getHealth(), 0,1 , true);
        this.heroType = heroAsset.getType();
        this.startMana = heroAsset.getStartMana();
    }

    public void die(){

    }

    @Override
    public HeroType getHeroType() {
        return this.heroType;
    }

    @Override
    public int getStartMana() {
        return this.startMana;
    }

    @Override
    public String toString() {
        return "Hero{" + "heroType=" + heroType + '}';
    }
}
