package issou.logic.utils;

import com.smartfoxserver.v2.entities.User;
import issou.logic.game.Player;

import java.util.*;

public class Players {

    private final Map<User, Player> players = new HashMap<>();
    private final List<User> usersReady = new ArrayList<>();
    private Player userPlaying;

    public void changeUserPlaying(){
        userPlaying = userPlaying.getOtherPlayer();
    }

    // to start the game

    public void add(User user, Player player){
        players.put(user, player);
        usersReady.add(user);
    }
    public boolean readyToStart(){
        return usersReady.size() == 2;
    }
    public void randomlyChooseFirstPlayer(){
        boolean first = RandomFactory.aBoolean();
        for(Player player : players.values()){
            if(!first) userPlaying = player; // pour que au début du prochaine tour ce soir l'autre qui joue
            first = !first;
        }
    }

    // GETTERS

    public Collection<Player> get(){
        return players.values();
    }

    public List<User> getUsers(){
        return usersReady;
    }

    public Player get(User user){
        return players.get(user);
    }

    public Player getUserPlaying(){
        return userPlaying;
    }
}
