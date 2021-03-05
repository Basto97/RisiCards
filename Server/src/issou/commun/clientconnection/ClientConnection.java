package issou.commun.clientconnection;

import issou.cli.Cli;
import issou.commun.logic.objects.card.ICard;

import java.util.List;

public class ClientConnection implements IClientConnection {

    // sfs2x object userid
    private final boolean first;

    public ClientConnection(boolean first){
        this.first = first;
    }

    @Override
    public void startGame(List<ICard> cards){
        StringBuilder cardsStr = new StringBuilder();
        cardsStr.append("first : ").append(first).append("\n");
        cardsStr.append("Starting cards :\n");
        for(int i = 0 ; i < cards.size() ; i++)
            cardsStr.append(i).append(" : ").append(cards.get(i)).append("\n");
        cardsStr.append( "Keep them ? (y/n)");
        addLog(cardsStr.toString());
    }

    private void addLog(String msg){
        Cli.addLog(first, msg);
    }
}
