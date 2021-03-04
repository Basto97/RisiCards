package issou.commun.logic.objects;

import java.util.*;

public class Deck {

    private final Queue<Card> cards = new LinkedList<>();

    public void shuffle(){
        Collections.shuffle((List<?>) cards);
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
