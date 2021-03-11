package issou;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;
import issou.sfs.utils.CreateRisitasGameRoomSettings;

import java.util.Collection;

public class RisicardsZoneExtension extends SFSExtension {

    @Override
    public void init() {
        addRequestHandler("content", (user, isfsObject) -> send("content", Content.serializedContent, user));
        addRequestHandler("play", (user, isfsObject) -> {
            try  {
                CreateRoomSettings cfg = CreateRisitasGameRoomSettings.get(user);
                getApi().createRoom(getParentZone(), cfg, null);
            } catch (SFSCreateRoomException e) {
                e.printStackTrace();
            }
        });
        addRequestHandler("join", ((user, isfsObject) -> {
            Room room = getParentZone().getRoomById(isfsObject.getInt("room"));
            if(room.getUserList().size() == 2){
                send("full", new SFSObject(), user);
                return;
            }

            String heroStr = isfsObject.getUtfString("hero");
            Collection<String> deckStr = isfsObject.getUtfStringArray("deck");

            try {
                RisicardsGameExtension ext = (RisicardsGameExtension) room.getExtension();
                ext.gameConfig.addUserConfig(user, heroStr, deckStr);
                getApi().joinRoom(user,room);
            } catch (SFSException e) {
                e.printStackTrace();
            }
        }));
    }

}
