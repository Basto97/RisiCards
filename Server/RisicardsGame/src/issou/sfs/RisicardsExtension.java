package issou.sfs;

import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.game.Game;
import issou.sfs.utils.Load;

public class RisicardsExtension extends SFSExtension {
    private Game game;
    @Override
    public void init() {
        Load.init(this);
        trace(Load.card("Risitas"));
    }
}
