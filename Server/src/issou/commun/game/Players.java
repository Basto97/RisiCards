package issou.commun.game;

import issou.cli.log.LogDest;

import static issou.commun.logic.utils.Enums.GameState.TurnP1;
import static issou.commun.logic.utils.Enums.GameState.TurnP2;

public class Players {

    private final Player[] players = new Player[2];
    private final Game game;

    public Players(Game game){
        this.game = game;
    }

    public Player getCurrent(){
        if(game.getGameState() == TurnP1)
            return players[0];
        else if(game.getGameState() == TurnP2)
            return players[1];
        throw new IllegalArgumentException();
    }

    public Player get(int i){
        if(i != 1 && i != 0) throw new IllegalArgumentException();
        return players[i];
    }

    public void set(int i, Player p){
        if(i != 1 && i != 0) throw new IllegalArgumentException();
        players[i] = p;
    }
}
