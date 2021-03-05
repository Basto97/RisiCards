package issou.commun.clientconnection;

import issou.commun.logic.objects.card.ICard;
import issou.commun.logic.objects.hand.IHand;

public interface IClientConnection {
    void newTurn();
    void drawCard(ICard card);
    void startGame(IHand hand);
    void startGameNewHand(IHand newHand);
}
