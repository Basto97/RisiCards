package issou.logic.game;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import issou.logic.objects.*;
import issou.logic.utils.Players;
import issou.sfs.api.SFSerializable;

public class Player implements SFSerializable {

    private final User user;
    private final Deck deck;
    private final Hand hand;
    private final Table table;
    private final Pool pool;
    private final HeroPower heroPower;
    private final Hero hero;

    private final Players players;
    private Player otherPlayer;

    public Player(Players players, User user, GameConfig config) {
        this.players = players;
        this.user = user;
        this.hero = config.hero;
        this.deck = config.deck;
        this.heroPower = config.heroPower;
        this.pool = new Pool();
        this.hand = new Hand();
        this.table = new Table();
    }

    // actions

    public boolean canDraw(){
        return deck.size() > 0;
    }
    public Card drawCard(){
        Card drawed = deck.draw();
        hand.addCard(drawed);
        return drawed;
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

    @Override
    public ISFSObject toSFS(){
        ISFSObject obj = new SFSObject();
        obj.putInt("handSize", hand.size());
        obj.putSFSObject("pool", pool.toSFS());
        obj.putSFSObject("hero", hero.toSFSObject());
        obj.putSFSObject("heroPower", heroPower.toSFSObject());
        obj.putInt("deckSize", deck.size());
        return obj;
    }

    public User getUser() {
        return user;
    }
    public Deck getDeck() {
        return deck;
    }
    public Hand getHand(){
        return hand;
    }
    public Pool getPool() {
        return pool;
    }
    @Override
    public String toString() {
        return "Player{" +
                "user=" + user +
                ", hand=" + hand +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return user.getId() == player.user.getId();
    }
}
