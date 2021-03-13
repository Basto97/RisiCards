package issou;

import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.logic.game.Game;
import issou.logic.game.GameConfig;

import static com.smartfoxserver.v2.core.SFSEventType.USER_JOIN_ROOM;
import static com.smartfoxserver.v2.core.SFSEventType.USER_LEAVE_ROOM;

public class RisicardsGameExtension extends SFSExtension {

    public GameConfig gameConfig = new GameConfig();

    private Game game;

    @Override
    public void init() {
        game = new Game(this);
        addEventHandler(USER_LEAVE_ROOM, isfsEvent -> {
            User leaver = (User) isfsEvent.getParameter(SFSEventParam.USER);
            gameConfig.removerUserConfig(leaver);
        });
        addRequestHandler("readyToStartGame", (user, isfsObject) -> game.playerReadyToStart(user , gameConfig));
                            // TODO isfsobject.getsfs('gameconfig') pour avoir le decks et hero choisi
        addRequestHandler("endTurn", (user, isfsObject) -> game.playerEndTurn(user));
    }
}
