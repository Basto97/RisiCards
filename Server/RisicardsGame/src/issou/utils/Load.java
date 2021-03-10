package issou.utils;

import issou.RisicardsZoneExtension;
import issou.logic.objects.HeroPower;
import issou.logic.objects.caracters.Hero;
import issou.logic.objects.card.Card;
import issou.ext.RisicardsGameExtension;

public class Load {

    private static RisicardsZoneExtension extension;

    public static void init(RisicardsGameExtension extension){
        Load.extension = (RisicardsZoneExtension) extension.getParentZone().getExtension();
    }

    public static Card card(String name){
        return extension.getContent().getCard(name);
    }

    public static Hero hero(String name){
        return extension.getContent().getHero(name);
    }

    public static HeroPower heroPower(String name){
        return extension.getContent().getHeroPower(name);
    }

    public static int maxMana(){
        return extension.getContent().getMaxMana();
    }

    public static int maxCardsHand(){
        return extension.getContent().getMaxCardsHand();
    }

    public static int initialDraw(){
        return extension.getContent().getInitialDraw();
    }

}
