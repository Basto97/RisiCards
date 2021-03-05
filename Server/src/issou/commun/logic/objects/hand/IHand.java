package issou.commun.logic.objects.hand;

import issou.commun.logic.objects.card.ICard;

import java.util.List;

public interface IHand {
    void addCard(ICard card);
    List<ICard> dropTheseCards(List<Integer> ids);
    boolean isFull();
}
