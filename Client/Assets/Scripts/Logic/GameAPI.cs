using Sfs2X.Entities.Data;
using UnityEngine;

public class GameAPI : MonoBehaviour {

    public static GameAPI Instance;
    
    public GameState gs;
    public VisualAPI va;

    private void Awake() => Instance = this;
    
    // CLIENT CALLS

    public void EndTurn() {
        // SmartFoxConnection.Send("endTurn");
    }

    // SERVER CALLS
    
    public void NewGame(SFSObject publicState) {
        gs = new GameState(publicState);
        va.Init(gs);
    }

    public void Draw(SFSObject obj) {
        Card drawed = new Card(obj.GetSFSObject("card"));
        gs.Player.DeckSize--;
        gs.Player.Hand.AddCard(drawed);
        va.Draw();
    }

    public void OpponantDraw(SFSObject obj) {
        gs.Opponant.DeckSize--;
        gs.Opponant.HandSize++;
        va.OpponantDraw();
    }

    public void NewTurn(SFSObject obj) {
        // gs.MyTurn = obj.GetInt("user").Equals(SmartFoxConnection.sfs.MySelf.Id);
        gs.TimeToPlay = obj.GetFloat("time");
        
        gs.UserPlaying.Pool = new Pool(obj.GetSFSObject("pool"));

        va.NewTurn();
    }

}