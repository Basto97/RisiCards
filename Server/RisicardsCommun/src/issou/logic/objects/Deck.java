package issou.logic.objects;

import issou.logic.objects.card.Card;

import java.util.*;

public class Deck {

    private final Queue<Card> cards = new LinkedList<>();

    public void addCard(Card card){
        this.cards.add(card);
    }
    public void shuffle(){
        Collections.shuffle((List<?>) this.cards);
    }
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }
    public int size(){
        return this.cards.size();
    }
    public Card draw()
    {
        return this.cards.poll();
    }
}
