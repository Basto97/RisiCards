public class UpdateManaCrystalsCommand : Command {

    private Player _p;
    private int _totalMana;
    private int _availableMana;

    public UpdateManaCrystalsCommand(Player p, int totalMana, int availableMana)
    {
        _p = p;
        _totalMana = totalMana;
        _availableMana = availableMana;
    }

    protected override void StartCommandExecution()
    {
        // _p.PArea.ManaBar.TotalCrystals = _totalMana;
        // _p.PArea.ManaBar.AvailableCrystals = _availableMana;
        CommandExecutionComplete();
    }
}
