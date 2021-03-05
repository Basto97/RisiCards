package issou.commun.logic.caracters.player;

import issou.commun.clientconnection.IClientConnection;
import issou.commun.logic.caracters.Character;
import issou.commun.logic.caracters.hero.IHero;
import issou.commun.logic.objects.deck.IDeck;
import issou.commun.logic.objects.hand.Hand;
import issou.commun.logic.objects.hand.IHand;
import issou.commun.logic.objects.heropower.IHeroPower;
import issou.commun.logic.objects.manapool.IManaPool;
import issou.commun.logic.objects.manapool.ManaPool;
import issou.commun.logic.objects.table.ITable;
import issou.commun.logic.objects.table.Table;

public class Player extends Character implements IPlayer {

    public IDeck deck;
    public IHand hand;
    public ITable table;
    public IManaPool manaPool;
    public IHeroPower heroPower;
    public IHero hero;
    public IClientConnection con;

    public Player(IHero hero, IHeroPower heroPower, IDeck deck) {
        super(1,0,1,true);
        this.hero = hero;
        this.deck = deck;
        this.heroPower = heroPower;
        this.manaPool = new ManaPool(hero.getStartMana());
        this.hand = new Hand();
        this.table = new Table();
    }

    public void onTurnStart() {
        manaPool.increaseManaMax(1);
        manaPool.resetCurrentMana();
        // heroPower.reset();
        // table.onTurnStart();
    }

    @Override
    public void die() {

    }

    @Override
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
