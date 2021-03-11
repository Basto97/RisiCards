package issou.logic.game;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import issou.collection.Content;
import issou.logic.objects.Deck;
import issou.logic.objects.HeroPower;
import issou.logic.objects.ManaPool;
import issou.logic.objects.Hero;

import java.util.*;

public class GameConfig {

    public Set<User> users = new HashSet<>();
    public Map<User,HeroPower> heroPowers = new HashMap<>();
    public Map<User,Hero> heros =  new HashMap<>();
    public Map<User,Deck> decks = new HashMap<>();
    public Map<User,ManaPool> manaPools = new HashMap<>();

    public void addUserConfig(User user, String heroStr, Collection<String> deckStr) throws SFSException {
        heroStr = "MageNoir"; // DEBUG
        deckStr = Arrays.asList("Risitas,Risitas,Risitas,JohnOfTheGuarden".split(","));
        if(users.size()==2)
            throw new SFSException("Trying to join a full room");
        if(!Content.isHero(heroStr))
            throw new SFSException("Hero "+heroStr+" is not an hero.");
        if(deckStr.size() > Content.cardsPerDeck[1])
            throw new SFSException("Size of deckstr "+deckStr.size()+" highter then max"+Content.cardsPerDeck[1]+".");
        if(deckStr.size() < Content.cardsPerDeck[0])
            throw new SFSException("Size of deckstr "+deckStr.size()+" smaller then mmin"+Content.cardsPerDeck[0]+".");

        Deck deck = new Deck();
        for (String cardName : deckStr)
            if(Content.isCard(cardName))
                deck.addCard(Content.getCard(cardName));
        deck.shuffle();
        decks.put(user,deck);
        heros.put(user,Content.getHero(heroStr));
        manaPools.put(user, new ManaPool(Content.getStartMana(heroStr)));
        heroPowers.put(user, Content.getHeroPowerFromHeroName(heroStr));
        users.add(user);
    }

    public void removerUserConfig(User user){
        heroPowers.remove(user);
        heros.remove(user);
        decks.remove(user);
        manaPools.remove(user);
        users.remove(user);
    }
}
