package issou.collection.assets;

public abstract class CardAsset{

    protected final int id;
    protected final int manaCost;

    protected CardAsset(int id, int manaCost) {
        this.id = id;
        this.manaCost = manaCost;
    }

    public boolean equals(CardAsset o) {
        return id == o.id;
    }

    public abstract CardAsset clone();
}
