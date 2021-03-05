package issou.commun.clientconnection;

import issou.commun.logic.objects.hand.IHand;

public interface IClientConnection {
    void startGame(IHand hand);
    void startGameNewHand(IHand newHand);
}
