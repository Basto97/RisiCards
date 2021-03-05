package issou.commun.game;

import issou.commun.clientconnection.ClientConnection;
import issou.commun.collection.content.Content;
import issou.commun.logic.caracters.player.Player;
import issou.commun.logic.objects.card.ICard;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Player[] players = new Player[2];
    private boolean turnPlayerOne = true;

    public Game(GameConfig config){
        for(int i = 0 ; i < 2 ; i++){
            players[i] = new Player(config.getHero(i), config.getHeroPower(i), config.getDeck(i));
            List<ICard> startCards = new ArrayList<>();
            for(int j = 0 ; j < Content.Instance.initialDraw(); j++)
                startCards.add(players[i].deck.draw());
            players[i].con = new ClientConnection(i == 0);    // add sfs2x id to client connection
            players[i].con.startGame(startCards);
        }
    }


}
