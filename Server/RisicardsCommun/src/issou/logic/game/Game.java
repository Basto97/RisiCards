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

    private final SFSExtension ext;
    private Users users = new Users();

    private User turnOf;

    public Game(SFSExtension ext) {
        this.ext = ext;
    }

    public void playerReadyToStart(User user, GameConfig config){
        Hero hero = config.heros.get(user);
        HeroPower heroPower = config.heroPowers.get(user);
        Deck deck = config.decks.get(user);
        ManaPool manaPool = config.manaPools.get(user);
        Player player = new Player(hero, heroPower, deck, manaPool);
        users.add(user, player);
        if(!users.readyToStart(user)) return;
        this.ext.send("startGame", users.publicState(), users.getUsers());
        boolean first = RandomFactory.aBoolean();
        User firstUser = null;
        for (UserPlayer up : users.get()) {
            int cardsStart = Content.initialDraw;
            if(first) {
                firstUser = up.user;
                cardsStart++;
            }
            for(int i = 0 ; i < cardsStart; i++)
                draw(up.user);
            first = !first;
        }
        turnOf(firstUser);
    }

    public void playerEndTurn(User user) {
        if(!user.equals(turnOf)) return;
        turnOf(users.other(user));
        draw(turnOf);
    }

    // helpers

    private void draw(User user){
        ISFSObject obj = new SFSObject();
        if(users.player(user).deck.isEmpty()){
            // TODO take damages
            return;
        }
        Card c = users.player(user).drawCard();
        obj.putInt("user", user.getId());
        obj.putInt("newDeckSize", users.player(user).deck.size());
        ext.send("draw", obj, users.other(user));
        obj.putSFSObject("card", c.toSFSObject());
        ext.send("draw", obj, user);
    }

    private void turnOf(User user){
        turnOf = user;
        ISFSObject obj = new SFSObject();
        obj.putInt("user", user.getId());
        obj.putFloat("time",Content.timePerTurn);
        ext.send("newTurn", obj,users.getUsers());
    }
}
