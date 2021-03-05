package issou.commun.collection.assets.card;

import issou.commun.collection.content.Content;
import org.json.JSONObject;

public abstract class CardAsset implements ICardAsset{

    private final String name;
    protected final int cardID;
    protected final int manaCost;

    public CardAsset(int cardID, JSONObject json) {
        this.cardID = cardID;
        this.manaCost = json.getInt("manaCost");
        this.name = json.getString("name");
    }

    public String getName(){
        return name;
    }
    public int getCardID(){
        return cardID;
    }
    public int getManaCost(){
        return manaCost;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAsset cardAsset = (CardAsset) o;
        return cardID == cardAsset.cardID;
    }
    public String toString() {
        return "name=" + name + ", manaCost=" + manaCost;
    }
}
