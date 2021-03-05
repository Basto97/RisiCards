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

    public List<ICard> newGameChangeCards(List<Integer> ids) {
        Predicate<ICard> mathId = c -> ids.contains(c.getId());
        List<ICard> cardsChanged = cardsInHand.stream().filter(mathId).collect(Collectors.toList());
        cardsInHand.removeIf(mathId);
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
