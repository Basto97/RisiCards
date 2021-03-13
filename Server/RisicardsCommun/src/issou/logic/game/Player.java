package issou.logic.game;

import issou.logic.objects.*;

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

    public Card drawCard(){
        Card drawed = deck.draw();
        hand.addCard(drawed);
        return drawed;
    }
}
