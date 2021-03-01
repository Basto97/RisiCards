package issou.commun.logic.objects;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    static final int MAX_CARDS = 10;

    public List<Card> cardsInHand = new ArrayList<>();

    public boolean isFull(){
        return cardsInHand.size() >= MAX_CARDS;
    }

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
}
