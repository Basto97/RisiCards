package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.collection.assets.CardAsset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card extends Identifiable implements SerializableSFSType {

    public final String name;
    public final boolean minion;
    public List<String> effects;
    public List<Integer> special;
    public int cost;
    public int costModification;

    public int attack;
    public int attackModification;
    public int health;
    public int healthModification;
    public String type;

    public String targetType;

    public Card(CardAsset card)   {
        super();
        this.name = card.name;
        this.minion = card.minion;
        this.cost = card.cost;
        this.costModification = 0;
        this.attack = card.attack;
        this.health = card.health;
        this.type = card.type;
        this.targetType = card.targetType;
        this.special = new ArrayList<>(Arrays.asList(card.special));
        this.effects = new ArrayList<>(Arrays.asList(card.effects));
    }

    public SFSObject toSFSObject(){
        SFSObject obj = new SFSObject();
        obj.putUtfString("name", name);
        obj.putBool("minion", minion);
        obj.putInt("costModification", costModification);
        obj.putInt("cost", cost);
        obj.putIntArray("special", special);
        obj.putUtfStringArray("effects", effects);
        if(minion){
            obj.putInt("attack", attack);
            obj.putInt("attackModification", attackModification);
            obj.putInt("health", health);
            obj.putInt("healthModification", healthModification);
            obj.putUtfString("type", type);
        } else {
            obj.putUtfString("targetType", targetType);
        }
        return obj;
    }

    public boolean equals(Card card){
        return card.name.equals(name);
    }
}
