package issou.commun.logic.objects;

import issou.commun.collection.Content;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public List<Card> cardsInHand = new ArrayList<>();

    public void removeCard(Card c) {
        cardsInHand.remove(c);
    }

    public boolean receveCard(Card c)
    {
        if(isFull())
            return false;
        cardsInHand.add(c);
        return true;
    }

    public boolean isFull(){
        return cardsInHand.size() >= Content.Instance().maxCardsHand();
    }
}
