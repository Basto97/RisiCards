public class StartATurnCommand : Command {

    private Player _p;

    public StartATurnCommand(Player p)
    {
        _p = p;
    }

    protected override void StartCommandExecution()
    {
        // TurnManager.Instance.whoseTurn = _p;
        CommandExecutionComplete();
    }
}
