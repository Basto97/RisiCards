using UnityEngine;

public class GameController : MonoBehaviour {
    
    private VisualAPI _va;
    private GameAPI _ga;

    private void Awake() {
        _va = FindObjectOfType<VisualAPI>();
        _ga = new GameAPI();
        
        Command.executionQueueComplete += () => {
            if (_ga.WaitingUserPlay)
                _va.HighlightPlayableCards(_ga.State.Player);
        };
        SFS.OnExtension("startGame", o => {
            _ga.StartGame(new GameState(o));
            _va.StartGame(_ga.State);
        });
        SFS.OnExtension("draw", o => {
            Card drawed = new Card(o.GetSFSObject("card"));
            _ga.draw(drawed);
            _va.Draw(drawed, _ga.State.Player.DeckSize);
        });
        SFS.OnExtension("opponantDraw", o => {
            _ga.opponantDraw();
            _va.OpponantDraw(_ga.State.Opponant.DeckSize);
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