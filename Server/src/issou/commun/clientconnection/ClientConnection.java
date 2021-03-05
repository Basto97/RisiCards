package issou.commun.clientconnection;

import issou.cli.Cli;
import issou.commun.logic.objects.hand.IHand;

import static issou.cli.log.LogDest.*;

public class ClientConnection implements IClientConnection {

    // sfs2x object userid
    private final boolean first;

    public ClientConnection(boolean first){
        this.first = first;
    }

    public void startGame(IHand hand){
        StringBuilder cardsStr = new StringBuilder();
        cardsStr.append("first : ").append(first).append("\n");
        cardsStr.append("Starting cards : ");
        cardsStr.append(hand).append("\n");
        cardsStr.append( "Type ids of cards to change.");
        addLog(true, cardsStr.toString());
    }

    public void startGameNewHand(IHand newHand) {
        StringBuilder cardsStr = new StringBuilder();
        cardsStr.append("Starting cards : ");
        cardsStr.append(newHand).append("\n");
        cardsStr.append( "Your new Cards.");
        addLog(false, cardsStr.toString());
    }

    private void addLog(boolean needResponse, String msg){
        Cli.addLog(first ? One : Two, needResponse, msg);
    }
    private static void addLogBoth(boolean needResonse, String msg){
        Cli.addLog(Both, needResonse, msg);
    }
}
