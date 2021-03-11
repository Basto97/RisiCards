package issou;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;
import issou.logic.game.Game;
import issou.logic.game.GameConfig;

import static com.smartfoxserver.v2.core.SFSEventType.*;

public class RisicardsGameExtension extends SFSExtension {

    public GameConfig gameConfig = new GameConfig();

    private Game game;

    @Override
    public void init() {
        addEventHandler(USER_JOIN_ROOM, new BaseServerEventHandler() {
            @Override
            public void handleServerEvent(ISFSEvent isfsEvent) {
                if(getParentRoom().getUserList().size() == 2) {
                    try {
                        game = new Game(gameConfig, RisicardsGameExtension.this);
                    } catch (SFSVariableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        addEventHandler(USER_LEAVE_ROOM, new BaseServerEventHandler() {
            @Override
            public void handleServerEvent(ISFSEvent isfsEvent) {
                User leaver = (User) isfsEvent.getParameter(SFSEventParam.USER);
                gameConfig.removerUserConfig(leaver);
            }
        });
    }
}
