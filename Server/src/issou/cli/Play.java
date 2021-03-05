package issou.cli;

import issou.cli.log.LogDest;
import issou.commun.game.Game;
import issou.commun.game.GameConfig;
import issou.commun.game.GameState;

import java.util.ArrayList;
import java.util.List;


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
        if(game.getGameState() == GameState.WaitingToStart1 || game.getGameState() == GameState.WaitingToStart2){
            String[] elems = res.split(" ");
            List<Integer> ids = new ArrayList<>();
            for (String elem : elems)
                if(!elem.equals(""))
                    ids.add(Integer.parseInt(elem));
            game.chooseFirstCards(logDest,ids);
        }
    }
}
