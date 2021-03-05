package issou.commun.game;

import issou.cli.log.LogDest;
import issou.commun.logic.objects.card.Card;
import issou.commun.logic.utils.Enums.GameState;

import java.util.List;

import static issou.commun.logic.utils.Enums.GameState.*;

public class Game {

    private final Players players = new Players(this);
    private GameState gameState = WaitingToStart1;

    public Game(GameConfig config){
        for(int i = 0 ; i < 2 ; i++){
            players.set(i, new Player(config.getHero(i), config.getHeroPower(i), config.getDeck(i), i==0));
            players.get(i).onGameStart();
            players.get(i).con.startGame( players.get(i).hand);
        }
    }

    // api

    public void newTurn(){
        players.getCurrent().con.newTurn();
        Card cardDrawed = players.getCurrent().deck.draw();

    }

    public void chooseFirstCards(int playerInt, List<Integer> cardsToChangeIds){
        switch (gameState){
            case WaitingToStart1 -> gameState = WaitingToStart2;
            case WaitingToStart2 -> gameState = TurnP1;
        }
        Player player = players.get(playerInt);
        player.onGameStartCardsChanged(cardsToChangeIds);
        player.con.startGameNewHand(player.hand);
    }

    public GameState getGameState() {
        return gameState;
    }
}
