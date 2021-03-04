package issou.commun.logic.caracters;

import issou.commun.collection.assets.HeroAsset;
import issou.commun.collection.assets.enums.HeroType;

public class Hero extends Character {

    private final HeroType heroType;

    public Hero(HeroAsset heroAsset) {
        super(heroAsset.getHealth());
        heroType = heroAsset.getType();
    }

    public void die(){

    }

    public HeroType getCharacterType() {
        return heroType;
    }
}
