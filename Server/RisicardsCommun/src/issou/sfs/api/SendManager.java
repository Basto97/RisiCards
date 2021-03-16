package issou.sfs.api;

import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.logic.utils.Players;

public class SendManager {

    private final SFSExtension ext;
    protected final Players players;

    public SendManager(SFSExtension ext, Players players) {
        this.ext = ext;
        this.players = players;
    }

    protected ISFSend req(String cmd){
        return new SFSend(ext, cmd, players);
    }
}
