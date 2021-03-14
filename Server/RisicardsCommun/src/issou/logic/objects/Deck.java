package issou.logic.objects;

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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Deck{");
        Iterator<Card> it = cards.iterator();
        while(it.hasNext()){
            sb.append(it.next());
            if(it.hasNext())
                sb.append('i');
        }
        sb.append('}');
        return sb.toString();
    }
}
