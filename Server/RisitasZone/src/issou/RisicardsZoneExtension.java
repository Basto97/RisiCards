package issou;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;

import java.util.Collection;

public class RisicardsZoneExtension extends SFSExtension {

    private static int i = 0;
    @Override
    public void init() {
        addRequestHandler("content", (user, isfsObject) -> send("content", Content.serializedContent, user));
        addRequestHandler("play", (user, isfsObject) -> {
            try  {
                CreateRoomSettings cfg = new CreateRoomSettings();
                cfg.setName("Partie de "+user.getName()+i+++".");
                cfg.setGame(true);
                cfg.setMaxUsers(2);
                cfg.setDynamic(true);
                cfg.setExtension(new CreateRoomSettings.RoomExtensionSettings("__lib__","issou.RisicardsGameExtension"));
                getApi().createRoom(getParentZone(), cfg, null);
            } catch (SFSCreateRoomException e) {
                e.printStackTrace();
            }
        });
        addRequestHandler("join", ((user, isfsObject) -> {
            Room room = getParentZone().getRoomById(isfsObject.getInt("room"));
            try {
                getApi().joinRoom(user,room);
            } catch (SFSException e) {
                e.printStackTrace();
            }
        }));
    }

}
