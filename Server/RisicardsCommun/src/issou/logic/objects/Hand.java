package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.collection.Content;

import java.util.ArrayList;
import java.util.List;

public class Hand implements SerializableSFSType {

    public List<Card> cardsInHand = new ArrayList<>();

    public void addCard(Card card) {
        cardsInHand.add(card);
    }

    public List<Card> dropTheseCards(List<Integer> ids) {
        List<Card> cardsChanged = new ArrayList<>();
        for(Card card : cardsInHand)
            if(ids.contains(card.getId()))
                cardsChanged.add(card);
        cardsInHand.removeIf(c -> ids.contains(c.getId()));
        return cardsChanged;
    }

    public boolean isFull(){
        return cardsInHand.size() >= Content.maxCardsHand;
    }

    public SFSArray toSFSArray(){
        SFSArray obj = new SFSArray();
        for(Card c: cardsInHand)
            obj.addSFSObject(c.toSFSObject());
        return obj;
    }

    public int size() {
        return cardsInHand.size();
    }
}
