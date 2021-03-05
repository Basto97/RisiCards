package issou.commun.game;

import issou.commun.collection.Content;
import issou.commun.collection.assets.enums.HeroType;
import issou.commun.logic.caracters.Hero;
import issou.commun.logic.caracters.IHero;
import issou.commun.logic.objects.deck.Deck;
import issou.commun.logic.objects.deck.IDeck;
import issou.commun.logic.objects.heropower.HeroPower;
import issou.commun.logic.objects.heropower.IHeroPower;

import java.util.Collection;

public class GameConfig {

    private final IHeroPower[] heroPowers = new HeroPower[2];
    private final IHero[] heros = new Hero[2];
    private final IDeck[] decks = new Deck[2];

    public GameConfig(int[] heros, Collection<Integer>[] decks) {
        assert this.heros.length == heros.length : "Heros size errors in creation of GameConfig";
        assert this.decks.length == decks.length : "Decks size errors in creation of GameConfig";

        // heros & hero powers
        for(int i = 0 ; i < this.heros.length ; i++){
            this.heros[i] = Content.Instance().getHero(HeroType.Get(heros[i]));
            this.heroPowers[i] = Content.Instance().getHeroPower(this.heros[i].getHeroPowerType());
        }

        // cards
        for(int i = 0 ; i < this.decks.length ; i++){
            this.decks[i] = new Deck();
            for(int j = 0; j < decks[i].size() ; j++)
                this.decks[i].addCard(Content.Instance().getCard(i));
        }
    }
}
