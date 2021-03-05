package issou.commun.logic.objects.deck;

import issou.commun.logic.objects.card.ICard;

import java.util.*;

public class Deck implements IDeck {

    private final Queue<ICard> cards = new LinkedList<>();

    public void addCard(ICard card){
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
    public ICard draw()
    {
        return this.cards.poll();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(cards.size()).append(" { ");
        for(ICard c : this.cards)
            sb.append(c.getName()).append(", ");
        sb.append("}");
        return sb.toString();
    }
}
