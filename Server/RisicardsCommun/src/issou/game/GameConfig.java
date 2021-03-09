package issou.game;

import issou.collection.Content;
import issou.logic.objects.caracters.Hero;
import issou.logic.objects.Deck;
import issou.logic.objects.HeroPower;

import java.util.List;

public class GameConfig {

    private final HeroPower[] heroPowers = new HeroPower[2];
    private final Hero[] heros = new Hero[2];
    private final Deck[] decks = new Deck[2];

    public GameConfig(String[] heros, List<String>[] decks) {
        assert this.heros.length == heros.length : "Heros size errors in creation of GameConfig";
        assert this.decks.length == decks.length : "Decks size errors in creation of GameConfig";

        // heros & hero powers
        for(int i = 0 ; i < this.heros.length ; i++){
            this.heros[i] = Content.getHero(heros[i]);
            //this.heroPowers[i] = Content.getHeroPower(heroType);
        }

        // cards
        for(int i = 0 ; i < this.decks.length ; i++){
            this.decks[i] = new Deck();
            for(int j = 0; j < decks[i].size() ; j++)
                //this.decks[i].addCard(Content.getCard(decks[i].get(j)));
            this.decks[i].shuffle();
        }
    }

    public Deck getDeck(int index){
        assert index == 0 || index == 1;
        return decks[index];
    }

    public Hero getHero(int index){
        assert index == 0 || index == 1;
        return heros[index];
    }

    public HeroPower getHeroPower(int index){
        assert index == 0 || index == 1;
        return heroPowers[index];
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-Heros : \n");
        for(Hero hero : heros)
            sb.append(" ").append(hero).append("\n");
        sb.append("-HeroPowers : \n");
        for(HeroPower heroPower : heroPowers)
            sb.append(" ").append(heroPower).append("\n");
        sb.append("-Decks : \n");
        for(Deck deck : decks)
            sb.append(" ").append(deck).append("\n");
        return sb.toString();
    }
}
