package issou.sfs.api;

import issou.logic.game.Player;

public interface ISFSend {
    ISFSend i(String key, int value);
    ISFSend s(String key, String value);
    ISFSend sfs(String key, SFSerializable value);
    ISFSend b(String key, boolean value);
    ISFSend f(String key, float value);
    ISFSend send(Player player);
    ISFSend sendAll();
}
