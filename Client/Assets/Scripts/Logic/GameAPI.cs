using Sfs2X.Entities.Data;
using UnityEngine;

public class GameAPI : MonoBehaviour {

    public static GameAPI Instance;
    
    public GameState gameState;
    public VisualAPI va;

    private void Awake() => Instance = this;
    
    // CLIENT CALLS

    public void EndTurn() {
        SmartFoxConnection.Send("endTurn");
    }

    // SERVER CALLS
    
    public void NewGame(SFSObject publicState) {
        gameState = new GameState(publicState);
        va.Init(gameState);
    }

    public void Draw(SFSObject obj) {
        Card drawed = new Card(obj.GetSFSObject("card"));
        gameState.Player.DeckSize--;
        gameState.Player.Hand.AddCard(drawed);
        va.Draw();
    }

    public void OpponantDraw(SFSObject obj) {
        gameState.Opponant.DeckSize--;
        gameState.Opponant.HandSize++;
        va.OpponantDraw();
    }

    public void NewTurn(SFSObject obj) {
        gameState.MyTurn = obj.GetInt("user").Equals(SmartFoxConnection.sfs.MySelf.Id);
        gameState.TimeToPlay = obj.GetFloat("time");
        
        gameState.UserPlaying.Pool = new Pool(obj.GetSFSObject("pool"));

        va.NewTurn();
    }

}