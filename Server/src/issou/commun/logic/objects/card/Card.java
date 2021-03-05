package issou.commun.logic.objects.card;

import issou.commun.collection.Content;
import issou.commun.collection.assets.card.ICardAsset;
import issou.commun.logic.utils.Identifiable;

public class Card extends Identifiable implements ICard {

    private final int cardID;
    private int manaCost;

    public Card(ICardAsset card)
    {
        super();
        this.cardID = card.getCardID();
        this.manaCost = card.getManaCost();
    }

    @Override
    public int getCardId() {
        return cardID;
    }
    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name=" + Content.Instance().getCardName(this.cardID)+
                ", manaCost=" + manaCost +
                '}';
    }
}
