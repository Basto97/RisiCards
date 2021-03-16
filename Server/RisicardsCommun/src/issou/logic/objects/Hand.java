package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.collection.Content;
import issou.sfs.api.SFSerializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hand implements SFSerializable {

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

    public SFSObject toSFS(){
        SFSArray cards = new SFSArray();
        for(Card c: cardsInHand)
            cards.addSFSObject(c.toSFSObject());
        SFSObject obj = new SFSObject();
        obj.putSFSArray("cards",cards);
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
