public class UpdateRopeCommand : Command {

    private readonly RopeVisual _rp;
    private readonly bool _playerTurn;
    private readonly float _timeToPlay;

    public UpdateRopeCommand(RopeVisual rp, bool playerTurn, float timeToPlay) {
        _rp = rp;
        _playerTurn = playerTurn;
        _timeToPlay = timeToPlay;
    }

    protected override void StartCommandExecution() {
        _rp.OnNewTurn(_playerTurn, _timeToPlay);
        CommandExecutionComplete();
    }
}
