package issou.cli;

import issou.commun.game.Game;
import issou.commun.game.GameConfig;

import java.util.List;


public class Play {

    private static Game game;

    public static void play(){
        String[] heroTypes = new String[2];
        heroTypes[0] = "ChevalierBlanc";
        heroTypes[1] = "MageNoir";
        List<Integer>[] decks = new List[2];
        decks[0] = Decks.decksInts.get(0);
        decks[1] = Decks.decksInts.get(1);
        GameConfig config = new GameConfig(heroTypes, decks);
        game = new Game(config);
    }
}
