public class DealDamageCommand : Command {

    private readonly int _targetID;
    private readonly int _amount;
    private readonly int _healthAfter;

    public DealDamageCommand( int targetID, int amount, int healthAfter)
    {
        _targetID = targetID;
        _amount = amount;
        _healthAfter = healthAfter;
    }

    protected override void StartCommandExecution()
    {
        /*
        GameObject target = IDHolder.GetGameObjectWithID(_targetID);
        if (_targetID == GlobalSettings.Instance.LowPlayer.PlayerID || _targetID == GlobalSettings.Instance.TopPlayer.PlayerID)
        {
            // target is a hero
            target.GetComponent<PlayerPortraitVisual>().TakeDamage(_amount,_healthAfter);
        }
        else
        {
            // target is a creature
            target.GetComponent<OneCreatureManager>().TakeDamage(_amount, _healthAfter);
        }
        CommandExecutionComplete();*/
    }
}
