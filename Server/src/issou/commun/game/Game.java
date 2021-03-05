package issou.commun.game;

import issou.commun.logic.caracters.player.Player;

public class Game {

    private final Player[] players = new Player[2];

    public Game(GameConfig config){
        for(int i = 0 ; i < 2 ; i++){
            players[i] = new Player(config.getHero(i), config.getHeroPower(i), config.getDeck(i), i==0);
            players[i].onGameStart();
        }
    }
}
