package issou.cli;

import issou.cli.log.Log;
import issou.cli.log.LogDest;
import issou.commun.collection.content.Content;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.out;

public class Cli {

    private static final Queue<Log> logs = new LinkedList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        Decks.loadDecks();
        while(true){
            while(logs.peek() != null){
                Log log = logs.poll();
                out.println(log);
                if(log.isNeedResponse()){
                    out.print("$ ");
                    String cmd = sc.nextLine();
                    Play.sendResponse(cmd, log.getLogDest());
                }
            }
            out.print("$ ");
            String cmd = sc.nextLine();
            gererCmd(cmd);
        }
    }

    private static void gererCmd(String cmd){
        String[] args = cmd.split(" ");
        switch (args[0]) {
            case "play" -> Play.play();
            case "decks" -> Decks.MontrerDecks();
            case "content" -> out.println(Content.Instance);
        }
    }

    public static void addLog(LogDest logDest, boolean needResponse, String msg){
        logs.add(new Log(logDest, needResponse, msg));
    }
}
