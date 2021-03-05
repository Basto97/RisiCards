package issou.cli;

import issou.cli.log.Log;
import issou.commun.collection.Content;
import issou.commun.collection.assets.enums.HeroType;
import issou.commun.game.Game;
import issou.commun.game.GameConfig;
import issou.commun.logic.objects.deck.Deck;
import issou.commun.logic.objects.deck.IDeck;

import java.util.*;

import static issou.commun.collection.assets.enums.HeroType.ChevalierBlanc;
import static issou.commun.collection.assets.enums.HeroType.MageNoir;

public class Cli {

    private static final Queue<Log> logs = new LinkedList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static boolean cmdReady;

    public static void main(String[] args){
        Decks.loadDecks();
        while(true){
            while(!cmdReady && logs.peek() != null)
                if(logs.peek() != null)
                    System.out.println(logs.poll());
            cmdReady = false;
            System.out.print("$ ");
            String cmd = sc.nextLine();
            gererCmd(cmd);
        }
    }

    private static void gererCmd(String cmd){
        String[] args = cmd.split(" ");
        switch (args[0]) {
            case "play" -> {
                int[] heroTypes = new int[2];
                heroTypes[0] = HeroType.Get(ChevalierBlanc);
                heroTypes[1] = HeroType.Get(MageNoir);
                List<Integer>[] decks = new List[2];
                decks[0] = Decks.decksInts.get(0);
                decks[1] = Decks.decksInts.get(1);
                GameConfig config = new GameConfig(heroTypes, decks);
                System.out.println(config);
            }
            case "decks" -> Decks.MontrerDecks();
            case "content" -> System.out.println(Content.Instance());
        }
    }

    public static void addLog(Log log){
        logs.add(log);
    }

    public static void cmdReady(){
        cmdReady = true;
    }
}
