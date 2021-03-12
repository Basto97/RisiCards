using Sfs2X.Entities.Data;
using UnityEngine;

public class GameAPI : MonoBehaviour {

    public PlayerVisual player;
    public PlayerVisual opponant;
    
    private State gameState;

    public void Init(SFSObject publicState) {
        gameState = new State(publicState);
        player.Init(gameState.player);
        // opponant.Init(gameState.opponnant);
    }
}