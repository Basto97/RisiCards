package issou.commun.collection.assets.card;

import issou.commun.collection.Content;
import org.json.JSONObject;

public abstract class CardAsset implements ICardAsset{

    protected final int cardID;
    protected final int manaCost;

    public CardAsset(int cardID, JSONObject json) {
        this.cardID = cardID;
        manaCost = json.getInt("manaCost");
    }

    @Override
    public int getCardID(){
        return cardID;
    }
    @Override
    public int getManaCost(){
        return manaCost;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAsset cardAsset = (CardAsset) o;
        return cardID == cardAsset.cardID;
    }
    @Override
    public String toString() {
        return "name=" + Content.Instance().getCardName(cardID) + ", manaCost=" + manaCost;
    }
}
