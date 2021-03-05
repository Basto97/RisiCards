package issou.commun.clientconnection;

import issou.commun.logic.objects.card.ICard;

import java.util.List;

public interface IClientConnection {
    void startGame(List<ICard> cards);
}
