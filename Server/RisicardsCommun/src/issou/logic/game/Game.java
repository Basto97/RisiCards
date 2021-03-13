package issou.logic.game;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;
import issou.logic.objects.*;
import issou.logic.utils.RandomFactory;
import issou.logic.utils.UserPlayer;
import issou.logic.utils.Users;

import java.util.*;

public class Game {

    private Users users = new Users();
    private final SFSExtension ext;

    public Game(GameConfig config, SFSExtension ext) throws SFSVariableException {
        this.ext = ext;

        for(User user : config.users){
            Hero hero = config.heros.get(user);
            HeroPower heroPower = config.heroPowers.get(user);
            Deck deck = config.decks.get(user);
            ManaPool manaPool = config.manaPools.get(user);
            Player player = new Player(hero, heroPower, deck, manaPool);
            users.add(user, player);
        }

        ISFSObject publicState = users.publicState();
        for(User user : config.users){
            ext.trace("STRAT GAME : "+user);
            this.ext.send("startGame", publicState, user);
        }

    }

    public void draw(User user){
        Card c = users.player(user).drawCard();
        ISFSObject obj = new SFSObject();
        obj.putUtfString("user", user.getName());
        ext.send("draw", obj, users.other(user));
        obj.putSFSObject("card", c.toSFSObject());
        ext.send("draw", obj, user);
    }

    public void playerReadyToStart(User user){
        if(!users.readyToStart(user)) return;

        boolean first = RandomFactory.aBoolean();
        for (UserPlayer up : users.get()) {
            int cardsStart = first ? Content.initialDraw +1 : Content.initialDraw;
            for(int i = 0 ; i < cardsStart; i++)
                draw(up.user);
            first = !first;
        }
    }

    // getters

    public ISFSObject privateState(User user, ISFSArray publicState){
        ISFSObject obj = new SFSObject();
        obj.putSFSArray("hand", users.player(user).hand.toSFSArray());
        obj.putSFSArray("publicStates", publicState);
        return obj;
    }
}
