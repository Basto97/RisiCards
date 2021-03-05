package issou.commun.game;

import issou.commun.clientconnection.ClientConnection;
import issou.commun.collection.Content;
import issou.commun.logic.objects.*;
import issou.commun.logic.objects.caracters.Character;
import issou.commun.logic.objects.caracters.Hero;
import issou.commun.logic.objects.card.Card;

import java.util.List;

public class Player {

    public Deck deck;
    public Hand hand;
    public Table table;
    public ManaPool manaPool;
    public HeroPower heroPower;
    public Hero hero;
    public ClientConnection con;

    private final boolean first;

    public Player(Hero hero, HeroPower heroPower, Deck deck, boolean first) {
        this.hero = hero;
        this.deck = deck;
        this.heroPower = heroPower;
        this.manaPool = new ManaPool(hero.getStartMana());
        this.hand = new Hand();
        this.table = new Table();
        this.con = new ClientConnection(first); // add sfs2x id to client connection instead of "first"
        this.first = first;
    }

    public void onGameStart(){
        int cardsStart = Content.getInitialDraw();
        if(first)
            cardsStart++;
        for(int i = 0 ; i < cardsStart; i++)
            hand.addCard(deck.draw());
    }

    public void onGameStartCardsChanged(List<Integer> cardsToChangeIds){
        List<Card> cardsDropped = hand.dropTheseCards(cardsToChangeIds);
        for(int i = 0 ; i < cardsDropped.size() ; i++)
            hand.addCard(deck.draw());
        for(Card card : cardsDropped)
            deck.addCard(card);
        deck.shuffle();
    }

    public String toString() {
        return "Player{\n" +
                "deck=" + deck +
                "\n hand=" + hand +
                "\n table=" + table +
                "\n manaPool=" + manaPool +
                "\n heroPower=" + heroPower +
                "\n hero=" + hero +
                "}\n";
    }
}
