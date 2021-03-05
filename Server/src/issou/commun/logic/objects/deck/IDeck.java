package issou.commun.logic.objects.deck;

import issou.commun.logic.objects.card.ICard;

public interface IDeck {
    void addCard(ICard card);
    void shuffle();
    boolean isEmpty();
    int size();
    ICard draw();
}
