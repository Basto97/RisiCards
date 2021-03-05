package issou.commun.game;

import issou.cli.log.LogDest;
import issou.commun.logic.caracters.player.Player;
import issou.commun.logic.objects.card.ICard;

import java.util.List;

import static issou.commun.game.GameState.*;

public class Game implements IGame {

    private final Player[] players = new Player[2];
    private GameState gameState = WaitingToStart1;

    public Game(GameConfig config){
        for(int i = 0 ; i < 2 ; i++){
            players[i] = new Player(config.getHero(i), config.getHeroPower(i), config.getDeck(i), i==0);
            players[i].onGameStart();
            players[i].con.startGame(players[i].hand);
        }
    }

    // api

    public void chooseFirstCards(LogDest logDest, List<Integer> cardsToChangeIds){
        if(gameState == WaitingToStart1)
            gameState = WaitingToStart2;
        else if (gameState == WaitingToStart2)
            gameState = TurnP1;
        List<ICard> cardsDropped = getPlayer(logDest).hand.dropTheseCards(cardsToChangeIds);
        for(int i = 0 ; i < cardsDropped.size() ; i++)
            getPlayer(logDest).hand.addCard(getPlayer(logDest).deck.draw());
        for(ICard card : cardsDropped)
            getPlayer(logDest).deck.addCard(card);
        getPlayer(logDest).deck.shuffle();
        getPlayer(logDest).con.startGameNewHand(getPlayer(logDest).hand);
    }

    // gets

    public Player getPlayer1() {
        return players[0];
    }
    public Player getPlayer2() {
        return players[1];
    }

    public Player getPlayer(LogDest logDest){
        if(logDest == LogDest.One)
            return players[0];
        else if(logDest == LogDest.Two)
            return players[1];
        throw new IllegalArgumentException();
    }

    public GameState getGameState() {
        return gameState;
    }
}
