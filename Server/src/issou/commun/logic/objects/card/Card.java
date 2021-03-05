package issou.commun.logic.objects.card;

import issou.commun.collection.assets.card.ICardAsset;
import issou.commun.logic.utils.Identifiable;

public class Card extends Identifiable implements ICard {

    private final String name;
    private int manaCost;

    public Card(ICardAsset card)
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

    public boolean equals(ICard card){
        return card.getName().equals(name);
    }
    public String toString() {
        return "Card{" +
                "name=" + name +
                ", manaCost=" + manaCost +
                '}';
    }
}
