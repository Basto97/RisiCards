package issou;

import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;
import issou.logic.objects.Deck;
import issou.logic.objects.caracters.Hero;

import java.util.Collection;

public class RisicardsCommunExtension extends SFSExtension {

    @Override
    public void init() {

        addRequestHandler("content", (user, isfsObject) -> send("content", Content.getSerializedContent(), user));
        addRequestHandler("play", (user, isfsObject) -> {

            String heroStr = isfsObject.getUtfString("hero");
            Hero hero = null;
            if(Content.isHero(heroStr))
                hero = Content.getHero(heroStr);

            Collection<String> cardsStr = isfsObject.getUtfStringArray("cards");
            Deck deck = new Deck();
            for (String cardName : cardsStr)
                if(Content.isCard(cardName))
                    deck.addCard(Content.getCard(cardName));


            System.out.println(hero);
            System.out.println(deck);
        });
    }

    private final Content content = new Content(); // to be used in game extensions
    public Content getContent(){
        return content;
    }
}
