package issou.logic.utils;

import com.smartfoxserver.v2.entities.User;
import issou.logic.game.Player;

public class UserPlayer {

    public User user;
    public Player player;

    public UserPlayer(User user, Player player){
        this.player = player;
        this.user = user;
    }

}
