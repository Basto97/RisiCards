using UnityEngine;

public class GameController : MonoBehaviour {
    
    private VisualAPI _va;
    private GameAPI _ga;

    private void Awake() {
        _va = FindObjectOfType<VisualAPI>();
        _ga = new GameAPI();
        
        Command.executionQueueComplete += () => _va.HighlighPlayable(_ga.state.PlayerTurn);
        SFS.OnExtension("startGame", o => {
            _ga.StartGame(new GameState(o));
            _va.StartGame(_ga.state);
        });
        SFS.OnExtension("draw", o => {
            Card drawed = new Card(o.GetSFSObject("card"));
            _ga.OnDraw(drawed);
            _va.Draw(drawed, _ga.state.Player.DeckSize);
        });
        SFS.OnExtension("opponantDraw", o => {
            _ga.OnOpponantDraw();
            _va.OpponantDraw(_ga.state.Opponant.DeckSize);
        });
        SFS.OnExtension("newTurn", o => {
            bool playerTurn = o.GetInt("user").Equals(SFS.MySelf.Id);
            float timeToPlay = o.GetFloat("time");
            Pool newPool = new Pool(o.GetSFSObject("pool"));
            _va.NewTurn(playerTurn, timeToPlay, newPool);
            _ga.NewTurn(playerTurn, timeToPlay, newPool);
        });
        
        SFS.Req("readyToStartGame").Send();
    }
    
    public void OnLeaveGameButtonClick() => new LeaveGameCommand().AddToQueue();
    public void OnEndTurnButtonClick() => SFS.Req("endTurn").Send();
}