package issou.commun.logic.objects;

import issou.commun.collection.assets.CardAsset;
import issou.commun.logic.utils.Player;
import issou.commun.logic.utils.Identifiable;

import java.util.HashMap;
import java.util.Map;

public class Card extends Identifiable {

    public static Map<Integer, Card> cardsCreatedThisGame = new HashMap<>();

    public Player owner;
    public CardAsset ca;
    // public SpellEffect Effect;

    public int manaCost;

    public Card(CardAsset ca)
    {
        super();
        this.ca = ca;
        cardsCreatedThisGame.put(getId(), this);
    }

    public int getManaCost(){
        return manaCost;
    }

    public boolean canBePlayed(){
        // TODO
        return true;
    }

}
