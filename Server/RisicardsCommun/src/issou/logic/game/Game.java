package issou.logic.game;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.logic.objects.Deck;
import issou.logic.objects.HeroPower;
import issou.logic.objects.ManaPool;
import issou.logic.objects.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public final Map<User, Player> players = new HashMap<>();
    public final List<User> users = new ArrayList<>();
    private final SFSExtension ext;

    public Game(GameConfig config, SFSExtension ext) throws SFSVariableException {
        this.ext = ext;

        for(User user : config.users){
            Hero hero = config.heros.get(user);
            HeroPower heroPower = config.heroPowers.get(user);
            Deck deck = config.decks.get(user);
            ManaPool manaPool = config.manaPools.get(user);
            Player player = new Player(hero, heroPower, deck, manaPool);
            players.put(user, player);
            users.add(user);
            player.onGameStart(user == users.get(0));
        }

        ISFSArray publicState = publicState();
        for(User user : config.users){
            ISFSObject privateState = privateState(user, publicState);
            this.ext.send("startGame", privateState, users);
        }

        RoomVariable roomVariable = new SFSRoomVariable("publicState", publicState());
        roomVariable.setPrivate(true);
        ext.getParentRoom().setVariable(roomVariable);
    }

    public void chooseFirstCards(User user, List<Integer> cardsToChangeIds){
        Player player = players.get(user);
        player.onGameStartCardsChanged(cardsToChangeIds);
    }

    // getters

    public ISFSArray publicState(){
        ISFSArray obj = new SFSArray();
        for (Map.Entry<User, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
            User user = entry.getKey();
            ISFSObject publicState = new SFSObject();
            publicState.putSFSObject("hero", player.hero.toSFSObject());
            publicState.putSFSObject("heroPower", player.heroPower.toSFSObject());
            publicState.putSFSObject("manaPool", player.manaPool.toSFSObject());
            publicState.putInt("deckSize", player.deck.size());
            publicState.putInt("handSize", player.hand.size());
            publicState.putUtfString("player", user.getName());
            obj.addSFSObject(publicState);
        }
        return obj;
    }

    public ISFSObject privateState(User user, ISFSArray publicState){
        ISFSObject obj = new SFSObject();
        obj.putSFSArray("hand", players.get(user).hand.toSFSArray());
        obj.putSFSArray("publicStates", publicState);
        return obj;
    }
}
