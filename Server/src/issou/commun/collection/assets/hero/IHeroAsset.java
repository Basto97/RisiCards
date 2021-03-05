package issou.commun.collection.assets.hero;

import issou.commun.collection.assets.enums.HeroPowerType;
import issou.commun.collection.assets.enums.HeroType;

public interface IHeroAsset {
    HeroType getType();
    HeroPowerType getHeroPowerType();
    int getHealth();
    int getStartMana();
}
