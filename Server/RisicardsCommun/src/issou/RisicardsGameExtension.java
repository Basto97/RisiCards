package issou;

import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;
import issou.logic.game.GameConfig;
import issou.logic.game.Player;
import issou.logic.objects.Card;
import issou.logic.utils.Players;

import java.util.ArrayList;

public class RisicardsGameExtension extends SFSExtension {

    private final Players players = new Players();
    private final CommunicationManager cm = new CommunicationManager(this, players);

    private GameConfig config = new GameConfig("", new ArrayList<>()); // DEBUG ONLY

    @Override
    public void init() {
        addRequestHandler("readyToStartGame", (user, isfsObject) -> playerReadyToStart(new Player(players, user, config.hero, config.heroPower, config.deck)));
        addRequestHandler("endTurn", (user, isfsObject) -> playerEndTurn(players.get(user)));
    }

    // Client calls

    public void playerReadyToStart(Player player){
        players.add(player.getUser(), player);
        if(players.readyToStart())
            startGame();
    }

    public void playerEndTurn(Player player) {
        if(!player.itsHisTurn()) return;
        newTurn();
    }


    private void startGame(){
        players.randomlyChooseFirstPlayer();
        cm.sendStartGame();

        for (Player player : players.get())
            for(int i = 0 ; i < Content.initialDraw; i++)
                draw(player);

        draw(players.getUserPlaying());
        newTurn();
    }

    // helpers

    private void draw(Player player){
        if(!player.canDraw()){
            // TODO take damages
            return;
        }
        Card c = player.drawCard();
        cm.sendDraw(player, c);
    }

    public void newTurn(){
        players.changePlayerPlaying();
        Player playerPlaying = players.getUserPlaying();

        playerPlaying.getManaPool().newTurn();

        cm.sendNewTurn(playerPlaying);

        draw(playerPlaying);
    }
}
