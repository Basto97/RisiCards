public class UpdatePoolCommand : Command {

    private readonly Pool _p;
    private readonly PoolVisual _pv;

    public UpdatePoolCommand(Pool p, PoolVisual pv) {
        _p = p;
        _pv = pv;
    }

    protected override void StartCommandExecution() {
        _pv.UpdatePoolVisual(_p);
        CommandExecutionComplete();
    }
}
