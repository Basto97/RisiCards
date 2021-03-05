package issou.cli;

import issou.cli.log.LogDest;
import issou.commun.game.Game;
import issou.commun.game.GameConfig;

import java.util.ArrayList;
import java.util.List;

import static issou.commun.logic.utils.Enums.GameState.WaitingToStart1;
import static issou.commun.logic.utils.Enums.GameState.WaitingToStart2;


public class Play {

    public static Game game;

    public static void play(){
        String[] heroTypes = new String[2];
        heroTypes[0] = "ChevalierBlanc";
        heroTypes[1] = "MageNoir";
        List<String>[] decks = new List[2];
        decks[0] = Decks.decksStr.get(0);
        decks[1] = Decks.decksStr.get(1);
        GameConfig config = new GameConfig(heroTypes, decks);
        game = new Game(config);
    }

    public static void sendResponse(String res, LogDest logDest){
        assert logDest != LogDest.Both;
        int playerInt = logDest == LogDest.One ? 0 : 1;
        if(game.getGameState() == WaitingToStart1 || game.getGameState() == WaitingToStart2) {   // to choose first cards
            String[] elems = res.split(" ");
            List<Integer> ids = new ArrayList<>();
            for (String elem : elems)
                if(!elem.equals(""))
                    ids.add(Integer.parseInt(elem));
            game.chooseFirstCards(playerInt,ids);
        }
    }
}
