package issou.commun.logic.caracters.hero;

import issou.commun.collection.enums.Types;
import issou.commun.logic.caracters.ICharacter;

public interface IHero extends ICharacter {
    Types.HeroType getHeroType();
    int getStartMana();
}
