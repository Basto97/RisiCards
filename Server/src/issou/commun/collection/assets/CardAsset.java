package issou.commun.collection.assets;

import org.json.JSONObject;

public abstract class CardAsset{

    protected final int id;
    protected final int manaCost;

    protected CardAsset(int id, int manaCost) {
        this.id = id;
        this.manaCost = manaCost;
    }

    public CardAsset(int id, JSONObject json) {
        this.id = id;
        manaCost = json.getInt("manaCost");
    }

    public boolean equals(CardAsset o) {
        return id == o.id;
    }

    public abstract CardAsset clone();
}
