package issou.commun.game;

import issou.commun.collection.Content;
import issou.commun.collection.assets.enums.HeroType;
import issou.commun.logic.caracters.hero.Hero;
import issou.commun.logic.caracters.hero.IHero;
import issou.commun.logic.objects.deck.Deck;
import issou.commun.logic.objects.deck.IDeck;
import issou.commun.logic.objects.heropower.HeroPower;
import issou.commun.logic.objects.heropower.IHeroPower;

import java.util.List;

public class GameConfig {

    private final IHeroPower[] heroPowers = new HeroPower[2];
    private final IHero[] heros = new Hero[2];
    private final IDeck[] decks = new Deck[2];

    public GameConfig(int[] heros, List<Integer>[] decks) {
        assert this.heros.length == heros.length : "Heros size errors in creation of GameConfig";
        assert this.decks.length == decks.length : "Decks size errors in creation of GameConfig";

        // heros & hero powers
        for(int i = 0 ; i < this.heros.length ; i++){
            HeroType heroType = HeroType.Get(heros[i]);
            this.heros[i] = Content.Instance().getHero(heroType);
            this.heroPowers[i] = Content.Instance().getHeroPower(heroType);
        }

        // cards
        for(int i = 0 ; i < this.decks.length ; i++){
            this.decks[i] = new Deck();
            for(int j = 0; j < decks[i].size() ; j++)
                this.decks[i].addCard(Content.Instance().getCard(decks[i].get(j)));
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-Heros : \n");
        for(IHero hero : heros)
            sb.append(" ").append(hero).append("\n");
        sb.append("-HeroPowers : \n");
        for(IHeroPower heroPower : heroPowers)
            sb.append(" ").append(heroPower).append("\n");
        sb.append("-Decks : \n");
        for(IDeck deck : decks)
            sb.append(" ").append(deck).append("\n");
        return sb.toString();
    }
}
