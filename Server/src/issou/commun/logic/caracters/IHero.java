package issou.commun.logic.caracters;

import issou.commun.collection.assets.enums.HeroPowerType;
import issou.commun.collection.assets.enums.HeroType;

public interface IHero extends ICharacter{
    HeroType getHeroType();
    HeroPowerType getHeroPowerType();
}
