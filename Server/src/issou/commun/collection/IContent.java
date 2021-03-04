package issou.commun.collection;

import issou.commun.logic.caracters.Character;
import issou.commun.logic.caracters.Hero;
import issou.commun.logic.objects.Card;
import issou.commun.logic.objects.HeroPower;

public interface IContent {
    Card getCard(int id);
    Hero getHero(int id);
    HeroPower getHeroPower(int id);
    int initialDraw();
    int maxCardsHand();
    int maxMana();
    String getCardName(int id);
    String toString();
    String toStringCards();
}
