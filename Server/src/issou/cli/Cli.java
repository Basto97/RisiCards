package issou.cli;

import issou.commun.game.Game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Cli {

    private static final Queue<Log> logs = new LinkedList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static boolean cmdReady;

    private static Game game;

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
        switch (args[0]){
            case "play":
                game = new Game();
                break;
            case "decks":
                Decks.GererDecks();
                break;
        }
    }

    public static void addLog(Log log){
        logs.add(log);
    }

    public static void cmdReady(){
        cmdReady = true;
    }
}
