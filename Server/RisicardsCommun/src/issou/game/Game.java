package issou.game;

import issou.logic.objects.card.Card;
import issou.logic.utils.Enums;

import java.util.List;

public class Game {

    private final Players players = new Players(this);
    private Enums.GameState gameState = Enums.GameState.WaitingToStart1;

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
            case WaitingToStart1 :
                gameState = Enums.GameState.WaitingToStart2;
                break;
            case WaitingToStart2 :
                gameState = Enums.GameState.TurnP1;
                break;
        }
        Player player = players.get(playerInt);
        player.onGameStartCardsChanged(cardsToChangeIds);
        player.con.startGameNewHand(player.hand);
    }

    public Enums.GameState getGameState() {
        return gameState;
    }
}
