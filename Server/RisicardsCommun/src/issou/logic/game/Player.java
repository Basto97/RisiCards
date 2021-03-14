package issou.logic.game;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.logic.objects.*;
import issou.logic.utils.Players;

public class Player implements SerializableSFSType {

    private final User user;
    private final Deck deck;
    private final Hand hand;
    private final Table table;
    private final ManaPool manaPool;
    private final HeroPower heroPower;
    private final Hero hero;

    private final Players players;
    private Player otherPlayer;

    public Player(Players players, User user, Hero hero, HeroPower heroPower, Deck deck) {
        this.players = players;
        this.user = user;
        this.hero = hero;
        this.deck = deck;
        this.heroPower = heroPower;
        this.manaPool = new ManaPool();
        this.hand = new Hand();
        this.table = new Table();
    }

    // actions

    public Card drawCard(){
        Card drawed = deck.draw();
        hand.addCard(drawed);
        return drawed;
    }

    public boolean canDraw(){
        return deck.size() > 0;
    }

    // util

    public boolean itsHisTurn(){
        return players.getUserPlaying().equals(this);
    }

    public Player getOtherPlayer(){
        if(otherPlayer != null)
            return otherPlayer;
        for(Player p : players.get())
            if(!p.equals(this))
                otherPlayer = p;
        return otherPlayer;
    }


    // others

    public ISFSObject toISFS(boolean all){
        ISFSObject obj = new SFSObject();
        if(all)
            obj.putSFSArray("hand", hand.toSFSArray());
        else
            obj.putInt("handSize", hand.size());
        obj.putSFSObject("pool", manaPool.toSFSObject());
        obj.putSFSObject("hero", hero.toSFSObject());
        obj.putSFSObject("heroPower", heroPower.toSFSObject());
        obj.putInt("deckSize", deck.size());
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return user.getId() == player.user.getId();
    }

    // GETTERS

    public User getUser() {
        return user;
    }

    public Deck getDeck() {
        return deck;
    }

    public ManaPool getManaPool() {
        return manaPool;
    }

}
