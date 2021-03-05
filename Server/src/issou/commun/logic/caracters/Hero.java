package issou.commun.logic.caracters;

import issou.commun.collection.assets.enums.HeroPowerType;
import issou.commun.collection.assets.hero.HeroAsset;
import issou.commun.collection.assets.enums.HeroType;
import issou.commun.collection.assets.hero.IHeroAsset;

public class Hero extends Character implements IHero {

    private final HeroType heroType;
    private final HeroPowerType heroPowerType;

    public Hero(IHeroAsset heroAsset) {
        super(heroAsset.getHealth(), 0,1 , true);
        this.heroType = heroAsset.getType();
        this.heroPowerType = heroAsset.getHeroPowerType();
    }

    public void die(){

    }

    @Override
    public HeroType getHeroType() {
        return this.heroType;
    }
    @Override
    public HeroPowerType getHeroPowerType() {
        return this.heroPowerType;
    }
}
