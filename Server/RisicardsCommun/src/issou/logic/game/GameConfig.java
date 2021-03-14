package issou.logic.game;

import com.smartfoxserver.v2.exceptions.SFSException;
import issou.collection.Content;
import issou.logic.objects.Deck;
import issou.logic.objects.Hero;
import issou.logic.objects.HeroPower;

import java.util.Arrays;
import java.util.Collection;

public class GameConfig {

    public HeroPower heroPower;
    public Hero hero;
    public Deck deck;

    public GameConfig(String heroStr, Collection<String> deckStr) {
        /*
        heroStr = "MageNoir"; // DEBUG
        deckStr = Arrays.asList("Risitas,Risitas,Risitas,JohnOfTheGuarden".split(","));
        if(!Content.isHero(heroStr))
            throw new SFSException("Hero "+heroStr+" is not an hero.");
        if(deckStr.size() > Content.cardsPerDeck[1])
            throw new SFSException("Size of deckstr "+deckStr.size()+" highter then max"+Content.cardsPerDeck[1]+".");
        if(deckStr.size() < Content.cardsPerDeck[0])
            throw new SFSException("Size of deckstr "+deckStr.size()+" smaller then mmin"+Content.cardsPerDeck[0]+".");

         */

        Deck deck = new Deck();
        for (String cardName : deckStr)
            if(Content.isCard(cardName))
                deck.addCard(Content.getCard(cardName));

        deck.shuffle();
        this.deck = deck;
        this.hero = Content.getHero(heroStr);
        this.heroPower = Content.getHeroPowerFromHeroName(heroStr);
    }

}
