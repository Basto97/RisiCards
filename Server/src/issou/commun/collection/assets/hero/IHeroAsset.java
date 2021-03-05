package issou.commun.collection.assets.hero;

import issou.commun.collection.enums.Types.HeroPowerType;
import issou.commun.collection.enums.Types.HeroType;

public interface IHeroAsset {
    HeroType getType();
    HeroPowerType getHeroPowerType();
    int getHealth();
    int getStartMana();
}
