package issou.commun.logic.objects.card;

import issou.commun.collection.assets.CardAsset;
import issou.commun.logic.utils.Identifiable;

public class Card extends Identifiable{

    private final String name;
    private int manaCost;

    public Card(CardAsset card)
    {
        super();
        this.name = card.getName();
        this.manaCost = card.getManaCost();
    }

    public String getName() {
        return name;
    }
    public int getManaCost() {
        return manaCost;
    }

    public boolean equals(Card card){
        return card.getName().equals(name);
    }
    public String toString() {
        return "Card "+getId()+"{" +
                "name=" + name +
                ", manaCost=" + manaCost +
                '}';
    }
}
