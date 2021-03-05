package issou.commun.logic.caracters.hero;

import issou.commun.collection.assets.enums.HeroType;
import issou.commun.logic.caracters.ICharacter;

public interface IHero extends ICharacter {
    HeroType getHeroType();
    int getStartMana();
}
