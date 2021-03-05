package issou.commun.logic.objects.hand;

import issou.commun.collection.content.Content;
import issou.commun.logic.objects.card.ICard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Hand implements IHand {

    public List<ICard> cardsInHand = new ArrayList<>();

    public void addCard(ICard card) {
        cardsInHand.add(card);
    }

    public List<ICard> dropTheseCards(List<Integer> ids) {
        List<ICard> cardsChanged = new ArrayList<>();
        for(ICard card : cardsInHand)
            if(ids.contains(card.getId()))
                cardsChanged.add(card);
        cardsInHand.removeIf(c -> ids.contains(c.getId()));
        return cardsChanged;
    }

    public boolean isFull(){
        return cardsInHand.size() >= Content.Instance.maxCardsHand();
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Hand{" +
                "cardsInHand={");
        for(ICard c : cardsInHand)
            s.append(c).append(", ");
        s.append("}}");
        return s.toString();
    }
}
