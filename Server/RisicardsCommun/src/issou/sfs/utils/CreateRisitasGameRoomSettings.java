package issou.sfs.utils;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.entities.User;

public class CreateRisitasGameRoomSettings {

    private static int i = 0;

    public static CreateRoomSettings get(User creator){
        CreateRoomSettings cfg = new CreateRoomSettings();
        cfg.setName("Partie de "+creator.getName()+i+++".");
        cfg.setGame(true);
        cfg.setMaxUsers(2);
        cfg.setDynamic(true);
        cfg.setExtension(new CreateRoomSettings.RoomExtensionSettings("__lib__","issou.RisicardsGameExtension"));
        return cfg;
    }
}
