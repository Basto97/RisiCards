package issou.commun.collection.content;

import issou.commun.collection.enums.Types.HeroPowerType;
import issou.commun.collection.enums.Types.HeroType;
import issou.commun.logic.caracters.hero.IHero;
import issou.commun.logic.objects.card.ICard;
import issou.commun.logic.objects.heropower.IHeroPower;

public interface IContent {
    ICard getCard(String name);
    IHero getHero(HeroType type);
    IHeroPower getHeroPower(HeroType type);
    IHeroPower getHeroPower(HeroPowerType type);
    int initialDraw();
    int maxCardsHand();
    int maxMana();
}
