package issou.commun.collection;

import issou.commun.collection.assets.CardAsset;
import issou.commun.collection.assets.CharacterAsset;
import issou.commun.collection.assets.HeroPowerAsset;

public interface IContent {
    CardAsset getCardAsset(int id);
    CharacterAsset getCharacterAsset(int id);
    HeroPowerAsset getHeroPowerAsset(int id);
    String getCardName(int id);
    int initialDraw();
    int maxCardsHand();
    int maxMana();
    String toString();
}
