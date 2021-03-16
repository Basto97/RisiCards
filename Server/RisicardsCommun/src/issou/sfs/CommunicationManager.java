package issou.sfs;

import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;
import issou.logic.game.Player;
import issou.logic.objects.Card;
import issou.logic.utils.Players;
import issou.sfs.api.SendManager;

public class CommunicationManager extends SendManager {

    public CommunicationManager(SFSExtension ext, Players players) {
        super(ext, players);
    }

    public void sendStartGame(){
        for (Player player : players.get())
            req("startGame").b("playerTurn", player.itsHisTurn()).sfs("player", player).sfs("hand", player.getHand()).sfs("opponant", player.getOtherPlayer()).send(player);
    }

    public void sendDraw(Player drawer, Card drawed){
        req("draw").sfs("card", drawed).send(drawer);
        req("opponantDraw").send(drawer.getOtherPlayer());
    }

    public void sendNewTurn(Player playerPlaying){
        req("newTurn").i("user",  playerPlaying.getUser().getId()).f("time", Content.timePerTurn).sfs("pool", playerPlaying.getPool()).sendAll();
    }


}
