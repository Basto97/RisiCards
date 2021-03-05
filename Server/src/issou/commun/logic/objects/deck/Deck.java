package issou.commun.logic.objects.deck;

import issou.commun.collection.content.Content;
import issou.commun.logic.objects.card.ICard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck implements IDeck {

    private final Queue<ICard> cards = new LinkedList<>();

    @Override
    public void addCard(ICard card){
        this.cards.add(card);
    }
    @Override
    public void shuffle(){
        Collections.shuffle((List<?>) this.cards);
    }
    @Override
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }
    @Override
    public int size(){
        return this.cards.size();
    }
    @Override
    public ICard draw()
    {
        return this.cards.poll();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(cards.size()).append(" { ");
        for(ICard c : this.cards)
            sb.append(Content.Instance.getCardName(c.getCardId())).append(", ");
        sb.append("}");
        return sb.toString();
    }
}
