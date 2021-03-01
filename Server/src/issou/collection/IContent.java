package issou.collection;

import issou.collection.assets.CardAsset;
import issou.collection.assets.CharacterAsset;
import issou.collection.assets.HeroPowerAsset;

public interface IContent {
    CardAsset getCardAsset(int id);
    CharacterAsset getCharacterAsset(int id);
    HeroPowerAsset getHeroPowerAsset(int id);
    int initialDraw();
    int maxCardsHand();
    int maxMana();
}
