package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.collection.Content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hand implements SerializableSFSType {

    private final List<Card> cardsInHand = new ArrayList<>();

    public void addCard(Card card) {
        cardsInHand.add(card);
    }
    public int size() {
        return cardsInHand.size();
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Hand{");
        Iterator<Card> it = cardsInHand.listIterator();
        while(it.hasNext()){
            sb.append(it.next());
            if(it.hasNext())
                sb.append('i');
        }
        sb.append('}');
        return sb.toString();
    }
}
