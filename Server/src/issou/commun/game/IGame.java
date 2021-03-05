package issou.commun.game;

import issou.cli.log.LogDest;
import issou.commun.logic.caracters.player.Player;

import java.util.List;

public interface IGame {
    Player getPlayer1();
    Player getPlayer2();
    Player getPlayer(LogDest logDest);
    GameState getGameState();
    void chooseFirstCards(LogDest logDest, List<Integer> cardsToChangeIds);
}
