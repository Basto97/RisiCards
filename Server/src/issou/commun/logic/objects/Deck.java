package issou.commun.logic.objects;

import issou.commun.logic.objects.card.Card;

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
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(cards.size()).append(" { ");
        for(Card c : this.cards)
            sb.append(c.getName()).append(", ");
        sb.append("}");
        return sb.toString();
    }
}
