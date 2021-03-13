using Sfs2X.Entities.Data;
using UnityEngine;

public class GameAPI : MonoBehaviour {
    public static GameAPI Instance;
    
    public PlayerVisual player;
    public PlayerVisual opponant;
    public RopeVisual rope;

    public State gameState;

    private void Awake() => Instance = this;
    
    public void HighLighPlayable() {
        
    }
    
    // CLIENT CALLS

    public void EndTurn() {
        SmartFoxConnection.Send("endTurn");
    }

    // SERVER CALLS
    
    public void NewGame(SFSObject publicState) {
        gameState = new State(publicState);
        player.Init(gameState.player);
        opponant.Init(gameState.opponnant);
    }

    public void Draw(SFSObject obj) {
        int newDeckSize = obj.GetInt("newDeckSize");
        if (obj.GetInt("user").Equals(SmartFoxConnection.sfs.MySelf.Id)) 
            new DrawACardCommand(new Card(obj.GetSFSObject("card")), newDeckSize, player.handVisual, player.deckVisual).AddToQueue();
        else 
            new DrawACardCommand(null,newDeckSize, opponant.handVisual,opponant.deckVisual).AddToQueue();
    }

    public void NewTurn(SFSObject obj) {
        gameState.myTurn = obj.GetInt("user").Equals(SmartFoxConnection.sfs.MySelf.Id);
        float time = obj.GetFloat("time");
        new ShowMessageCommand(gameState.myTurn ? "Votre tour." : "Tour de l'adversaire", 1.5f).AddToQueue();
        rope.OnNewTurn(gameState.myTurn, time);
    } 
    
}