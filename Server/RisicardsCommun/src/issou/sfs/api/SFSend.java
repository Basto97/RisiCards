package issou.sfs.api;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.logic.game.Player;
import issou.logic.utils.Players;

public class SFSend implements ISFSend {
    private final String cmd;
    private final ISFSObject obj;
    private final SFSExtension ext;
    private final Players players;

    public SFSend(SFSExtension ext, String cmd, Players players) {
        this.ext = ext;
        this.cmd = cmd;
        this.players = players;
        obj = new SFSObject();
    }

    @Override
    public ISFSend i(String key, int value) {
        obj.putInt(key, value);
        return this;
    }

    @Override
    public ISFSend s(String key, String value) {
        obj.putUtfString(key, value);
        return this;
    }

    @Override
    public ISFSend sfs(String key, SFSerializable value) {
        obj.putSFSObject(key, value.toSFS());
        return this;
    }

    @Override
    public ISFSend b(String key, boolean value) {
        obj.putBool(key, value);
        return this;
    }

    @Override
    public ISFSend f(String key, float value) {
        obj.putFloat(key, value);
        return this;
    }

    @Override
    public ISFSend send(Player player) {
        this.ext.send(cmd, obj, player.getUser());
        return this;
    }

    @Override
    public ISFSend sendAll() {
        this.ext.send(cmd, obj, players.getUsers());
        return this;
    }
}
