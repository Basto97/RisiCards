using Sfs2X.Entities.Data;
using UnityEngine;
using UnityEngine.Serialization;

public class GameController : MonoBehaviour {
    
    [SerializeField] private VisualAPI va;
    private GameState _gs;

    private void Awake() {
        Command.executionQueueComplete += () => {
            if (_gs != null && _gs.MyTurn && _gs.State == State.Playing)
                va.HighlightPlayableCards();
        };
        SFS.OnExtension("startGame", o => {
            _gs = new GameState(o);
            va.Init(_gs);
        });
        SFS.OnExtension("draw", o => {
            Card drawed = new Card(o.GetSFSObject("card"));
            _gs.Player.DeckSize--;
            _gs.Player.Hand.AddCard(drawed);
            va.Draw();
        });
        SFS.OnExtension("opponantDraw", o => {
            _gs.Opponant.DeckSize--;
            _gs.Opponant.HandSize++;
            va.OpponantDraw();
        });
        SFS.OnExtension("newTurn", o => {
            // gs.MyTurn = obj.GetInt("user").Equals(SmartFoxConnection.sfs.MySelf.Id);
            _gs.TimeToPlay = o.GetFloat("time");
            _gs.UserPlaying.Pool = new Pool(o.GetSFSObject("pool"));
            va.NewTurn();
        });
        
        SFS.Req("readyToStartGame").Send();
    }
    
    public void OnLeaveGameButtonClick() => new LeaveGameCommand().AddToQueue();
    public void OnEndTurnButtonClick() => SFS.Req("endTurn").Send();
}