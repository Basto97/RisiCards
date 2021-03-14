using System;

[Serializable]
public class PlayersVisual {
    
    public PlayerVisual Player;
    public PlayerVisual Opponant;

    public PlayersVisual(PlayerVisual player, PlayerVisual opponant) {
        Player = player;
        Opponant  = opponant;
    }
    
    public PlayerVisual UserPlaying => GameAPI.instance.gameState.myTurn ? Player : Opponant ;
    
}
