package issou.commun.collection.assets.card;

import org.json.JSONObject;

public abstract class CardAsset implements ICardAsset{

    private final String name;
    private final int manaCost;

    public CardAsset(JSONObject json) {
        this.manaCost = json.getInt("manaCost");
        this.name = json.getString("name");
    }

    public String getName(){
        return name;
    }
    public int getManaCost(){
        return manaCost;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAsset cardAsset = (CardAsset) o;
        return name.equals(cardAsset.getName());
    }
    public String toString() {
        return "name=" + name + ", manaCost=" + manaCost;
    }
}
