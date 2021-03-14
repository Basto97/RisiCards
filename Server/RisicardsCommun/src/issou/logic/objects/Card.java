package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.collection.assets.CardAsset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Card extends Identifiable implements SerializableSFSType {

    public final String name;
    public final boolean minion;
    public List<String> effects;
    public List<Integer> special;
    public int cost;
    public int costModification;
    public final String hero;

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
        this.hero = card.hero;
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
        obj.putUtfString("hero", hero);
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

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
