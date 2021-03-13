package issou.logic.utils;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import issou.logic.game.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Users {

    private final Map<User, Player> players = new HashMap<>();
    private final Set<UserPlayer> userPlayer = new HashSet<>();
    private final Set<User> playersReadyToStart = new HashSet<>();

    public void add(User user, Player player){
        players.put(user, player);
        userPlayer.add(new UserPlayer(user, player));
    }

    public boolean readyToStart(User user){
        playersReadyToStart.add(user);
        return playersReadyToStart.size() == 2;
    }

    public User other(User user) {
        for (Map.Entry<User, Player> entry : players.entrySet())
            if(!entry.getKey().equals(user))
                return entry.getKey();
        try{
            throw new SFSException("User impossible");
        } catch (SFSException e){
            e.printStackTrace();
        }
        return user;
    }

    public Player player(User user){
        return players.get(user);
    }

    public Set<UserPlayer> get(){
        return userPlayer;
    }

    public ISFSObject publicState(){
        SFSArray arr = new SFSArray();
        for (UserPlayer up : get()) {
            Player player = up.player;
            User user = up.user;
            ISFSObject publicState = new SFSObject();
            publicState.putSFSObject("hero", player.hero.toSFSObject());
            publicState.putSFSObject("heroPower", player.heroPower.toSFSObject());
            publicState.putSFSObject("manaPool", player.manaPool.toSFSObject());
            publicState.putInt("deckSize", player.deck.size());
            publicState.putInt("handSize", player.hand.size());
            publicState.putUtfString("player", user.getName());
            arr.addSFSObject(publicState);
        }
        ISFSObject obj = new SFSObject();
        obj.putSFSArray("players", arr);
        return obj;
    }
}
