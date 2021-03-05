package issou.commun.logic.objects;

import issou.commun.collection.Content;
import issou.commun.logic.objects.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public List<Card> cardsInHand = new ArrayList<>();

    public void addCard(Card card) {
        cardsInHand.add(card);
    }

    public List<Card> dropTheseCards(List<Integer> ids) {
        List<Card> cardsChanged = new ArrayList<>();
        for(Card card : cardsInHand)
            if(ids.contains(card.getId()))
                cardsChanged.add(card);
        cardsInHand.removeIf(c -> ids.contains(c.getId()));
        return cardsChanged;
    }

    public boolean isFull(){
        return cardsInHand.size() >= Content.getMaxCardsHand();
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Hand{" +
                "cardsInHand={");
        for(Card c : cardsInHand)
            s.append(c).append(", ");
        s.append("}}");
        return s.toString();
    }
}
