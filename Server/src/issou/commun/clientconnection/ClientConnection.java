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

    @Override
    public void startGame(IHand hand){
        StringBuilder cardsStr = new StringBuilder();
        cardsStr.append("first : ").append(first).append("\n");
        cardsStr.append("Starting cards :\n");
        cardsStr.append(hand).append("\n");
        cardsStr.append( "Keep them ? (y/n)");
        addLog(true, cardsStr.toString());
    }

    private void addLog(boolean needResponse, String msg){
        Cli.addLog(first ? One : Two, needResponse, msg);
    }
    private static void addLogBoth(boolean needResonse, String msg){
        Cli.addLog(Both, needResonse, msg);
    }
}
