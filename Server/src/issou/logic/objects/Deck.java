package issou.logic.objects;

import issou.collection.assets.CardAsset;

import java.util.*;

public class Deck {

    private final Queue<CardAsset> cards;

    public Deck(Set<CardAsset> cards)
    {
        Collections.shuffle((List<?>) cards);
        this.cards  = new LinkedList<>(cards);
    }

    public boolean IsEmpty() {
        return this.cards.isEmpty();
    }

    public int Size (){
        return this.cards.size();
    }

    public CardAsset Draw()
    {
        return this.cards.poll();
    }
}
