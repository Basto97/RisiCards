using Sfs2X.Entities.Data;
using UnityEngine;

public class GameAPI : MonoBehaviour {

    public PlayerVisual player;
    public PlayerVisual opponant;
    
    private State _gameState;

    public void Init(SFSObject publicState) {
        _gameState = new State(publicState);
        player.Init(_gameState.player);
        opponant.Init(_gameState.opponnant);
        
        SmartFoxConnection.Send("readyToStartGame");
    }

    public void Draw(SFSObject obj) {
        if(obj.GetUtfString("user").Equals(SmartFoxConnection.sfs.MySelf.Name))
            new DrawACardCommand(new Card(obj.GetSFSObject("card")), player.handVisual).AddToQueue();
        else 
            new DrawACardCommand(null, opponant.handVisual).AddToQueue();
    }
    
}