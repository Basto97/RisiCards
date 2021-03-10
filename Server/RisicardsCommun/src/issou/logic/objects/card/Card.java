package issou.logic.objects.card;

import issou.collection.assets.CardAsset;
import issou.logic.objects.Identifiable;

public class Card extends Identifiable {

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
    public String toString() {
        return "Card "+getId()+"{" +
                "name=" + name +
                ", cost=" + cost +
                '}';
    }
}
