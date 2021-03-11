package issou.logic.objects.card;

import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.collection.assets.CardAsset;
import issou.logic.objects.utils.Identifiable;

public class Card extends Identifiable implements SerializableSFSType {

    private final String name;
    private int cost;

    public Card(CardAsset card)
    {
        super();
        this.name = card.getName();
        this.cost = card.getCost();
    }

    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }

    public boolean equals(Card card){
        return card.getName().equals(name);
    }

    public SFSObject toSFSObject(){
        SFSObject obj = new SFSObject();
        obj.putUtfString("name", name);
        obj.putInt("cost", cost);
        return obj;
    }
}
