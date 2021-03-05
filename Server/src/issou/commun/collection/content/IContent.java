package issou.commun.collection.content;

import issou.commun.collection.assets.enums.HeroPowerType;
import issou.commun.collection.assets.enums.HeroType;
import issou.commun.logic.caracters.hero.IHero;
import issou.commun.logic.objects.card.ICard;
import issou.commun.logic.objects.heropower.IHeroPower;

public interface IContent {
    ICard getCard(int id);
    IHero getHero(HeroType type);
    IHeroPower getHeroPower(HeroType type);
    IHeroPower getHeroPower(HeroPowerType type);
    int initialDraw();
    int maxCardsHand();
    int maxMana();
    String getCardName(int id);
}
