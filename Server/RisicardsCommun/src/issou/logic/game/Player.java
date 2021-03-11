package issou.logic.game;

import issou.collection.Content;
import issou.logic.objects.*;
import issou.logic.objects.Hero;
import issou.logic.objects.Card;

import java.util.List;

public class Player {

    public Deck deck;
    public Hand hand;
    public Table table;
    public ManaPool manaPool;
    public HeroPower heroPower;
    public Hero hero;

    public Player(Hero hero, HeroPower heroPower, Deck deck, ManaPool manaPool) {
        this.hero = hero;
        this.deck = deck;
        this.heroPower = heroPower;
        this.manaPool = manaPool;
        this.hand = new Hand();
        this.table = new Table();
    }

    public void onGameStart(boolean first){
        int cardsStart = Content.initialDraw;
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
}
