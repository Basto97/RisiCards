package issou;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;
import issou.logic.objects.Deck;
import issou.logic.objects.caracters.Hero;

import java.util.Collection;

public class RisicardsZoneExtension extends SFSExtension {

    @Override
    public void init() {

        addRequestHandler("content", (user, isfsObject) -> send("content", Content.getSerializedContent(), user));
        addRequestHandler("play", (user, isfsObject) -> {
            CreateRoomSettings cfg = new CreateRoomSettings();
            cfg.setName("Partie de "+user.getName()+".");
            cfg.setGame(true);
            cfg.setMaxUsers(10);
            cfg.setDynamic(true);
            try  {
               getApi().createRoom(getParentZone(), cfg, null);
            }
            catch (SFSCreateRoomException e) {
                e.printStackTrace();
            }
        });
        addRequestHandler("join", ((user, isfsObject) -> {
            String heroStr = isfsObject.getUtfString("hero");
            Hero hero = null;
            if(Content.isHero(heroStr))
                hero = Content.getHero(heroStr);

            Collection<String> cardsStr = isfsObject.getUtfStringArray("cards");
            Deck deck = new Deck();
            for (String cardName : cardsStr)
                if(Content.isCard(cardName))
                    deck.addCard(Content.getCard(cardName));

            Room room = getParentZone().getRoomById(isfsObject.getInt("room"));
            if(room != null) {
                // user.setVariable(); (y mettre le deck et le hero) comment lier la variable à la room ?
                try {
                    getApi().joinRoom(user,room);
                } catch (SFSJoinRoomException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private final Content content = new Content(); // to be used in game extensions
    public Content getContent(){
        return content;
    }
}
