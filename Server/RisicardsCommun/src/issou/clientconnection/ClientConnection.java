package issou.clientconnection;

import issou.logic.objects.Hand;

public class ClientConnection {

    // sfs2x object userid
    private final boolean first;

    public ClientConnection(boolean first){
        this.first = first;
    }

    public void startGame(Hand hand){
        StringBuilder cardsStr = new StringBuilder();
        cardsStr.append("Starting cards : ");
        cardsStr.append(hand).append("\n");
        cardsStr.append( "Type ids of cards to change.");
        addLog(true, cardsStr.toString());
    }

    public void startGameNewHand(Hand newHand) {
        StringBuilder cardsStr = new StringBuilder();
        cardsStr.append("Starting cards : ");
        cardsStr.append(newHand).append("\n");
        cardsStr.append( "Your new Cards.");
        addLog(false, cardsStr.toString());
    }

    private void addLog(boolean needResponse, String msg){

    }
    private static void addLogBoth(boolean needResonse, String msg){

    }

    public void newTurn() {

    }
}
