package issou.commun.logic.objects.card;

import issou.commun.logic.utils.IIdentifiable;

public interface ICard extends IIdentifiable {
    int getCardId();
    int getManaCost();
}
