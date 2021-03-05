package issou.commun.logic.objects.hand;

import issou.commun.collection.content.Content;
import issou.commun.logic.objects.card.ICard;

import java.util.ArrayList;
import java.util.List;

public class Hand implements IHand {

    public List<ICard> cardsInHand = new ArrayList<>();

    public void removeCard(ICard c) {
        cardsInHand.remove(c);
    }

    public boolean receveCard(ICard c)
    {
        if(isFull()) return false;
        cardsInHand.add(c);
        return true;
    }

    public boolean isFull(){
        return cardsInHand.size() >= Content.Instance.maxCardsHand();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Hand{" +
                "cardsInHand={");
        for(ICard c : cardsInHand)
            s.append(c).append(", ");
        s.append("}}");
        return s.toString();
    }

}
