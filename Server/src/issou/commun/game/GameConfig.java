package issou.commun.game;

import issou.commun.collection.Content;
import issou.commun.collection.assets.CardAsset;
import issou.commun.collection.assets.CharacterAsset;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GameConfig {

    public Set<CardAsset> deckPlayer1;
    public Set<CardAsset> deckPlayer2;

    public CharacterAsset charPlayer1;
    public CharacterAsset charPlayer2;

    public GameConfig(int idCharPlayer1, Collection<Integer> deckUser1, int idCharPlayer2, Collection<Integer> deckUser2) {
        charPlayer1 = Content.Instance().getCharacterAsset(idCharPlayer1);
        charPlayer2 = Content.Instance().getCharacterAsset(idCharPlayer2);

        deckPlayer1 = new HashSet<>();
        deckPlayer2 = new HashSet<>();

        for(int i : deckUser1)
            deckPlayer1.add(Content.Instance().getCardAsset(i));
        for(int i : deckUser2)
            deckPlayer2.add(Content.Instance().getCardAsset(i));
    }
}
