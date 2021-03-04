package issou.commun.game;

import issou.commun.collection.Content;
import issou.commun.logic.caracters.Hero;
import issou.commun.logic.objects.Deck;

import java.util.Collection;

public class GameConfig {

    private final Hero[] heros = new Hero[2];
    private final Deck[] decks = new Deck[2];

    public GameConfig(int idCharPlayer1, Collection<Integer> deckUser1, int idCharPlayer2, Collection<Integer> deckUser2) {
        heros[0] = Content.Instance().getHero(idCharPlayer1);
        heros[1] = Content.Instance().getHero(idCharPlayer2);


        for(int i : deckUser1)
            decks[0] = new Deck(deckUser1);
        for(int i : deckUser2)
            decks[1] = new Deck(deckUser2);
    }
}
