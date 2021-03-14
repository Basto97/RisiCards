package issou;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;
import issou.logic.game.Player;
import issou.logic.objects.Card;
import issou.logic.utils.Players;

public class CommunicationManager {

    private final Players players;
    private final SFSExtension ext;

    public CommunicationManager(SFSExtension ext, Players players){
        this.ext = ext;
        this.players = players;
    }

    public void sendStartGame(){
        for (Player player : players.get()){
            ISFSObject obj = new SFSObject();
            obj.putSFSObject("player", player.toISFS(true));
            obj.putSFSObject("opponant", player.getOtherPlayer().toISFS(false));
            obj.putBool("first", player.itsHisTurn());
            ext.send("startGame", obj,player.getUser());
        }
    }

    public void sendDraw(Player drawer, Card drawed){
        ISFSObject obj = new SFSObject();
        obj.putSFSObject("card", drawed.toSFSObject());
        ext.send("draw", obj, drawer.getUser());
        ext.send("opponantDraw", new SFSObject(), drawer.getOtherPlayer().getUser());
    }

    public void sendNewTurn(Player playerPlaying){
        ISFSObject obj = new SFSObject();
        obj.putInt("user", playerPlaying.getUser().getId());
        obj.putFloat("time", Content.timePerTurn);
        obj.putSFSObject("pool", playerPlaying.getManaPool().toSFSObject());
        ext.send("newTurn", obj, players.getUsers());
    }
}
