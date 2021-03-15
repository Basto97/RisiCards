public class StartATurnCommand : Command {

    private readonly GameState _state;
    private readonly bool _playerTurn;

    public StartATurnCommand(GameState state, bool playerTurn) {
        _state = state;
        _playerTurn = playerTurn;
    }

    protected override void StartCommandExecution() {
        _state.PlayerTurn = _playerTurn;
        CommandExecutionComplete();
    }
}
