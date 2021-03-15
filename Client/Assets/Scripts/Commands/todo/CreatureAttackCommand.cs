public class CreatureAttackCommand : Command {
    // position of creature on enemy`s table that will be attacked
    // if enemyindex == -1 , attack an enemy character 
    private readonly int _targetUniqueID;
    private readonly int _attackerUniqueID;
    private readonly int _attackerHealthAfter;
    private readonly int _targetHealthAfter;
    private readonly int _damageTakenByAttacker;
    private readonly int _damageTakenByTarget;

    public CreatureAttackCommand(int targetID, int attackerID, int damageTakenByAttacker, int damageTakenByTarget, int attackerHealthAfter, int targetHealthAfter) {
        _targetUniqueID = targetID;
        _attackerUniqueID = attackerID;
        _attackerHealthAfter = attackerHealthAfter;
        _targetHealthAfter = targetHealthAfter;
        _damageTakenByTarget = damageTakenByTarget;
        _damageTakenByAttacker = damageTakenByAttacker;
    }

    protected override void StartCommandExecution() {
        // GameObject attacker = IDHolder.GetGameObjectWithID(_attackerUniqueID);
        // attacker.GetComponent<CreatureAttackVisual>().AttackTarget(_targetUniqueID, _damageTakenByTarget, _damageTakenByAttacker, _attackerHealthAfter, _targetHealthAfter);
    }
}
