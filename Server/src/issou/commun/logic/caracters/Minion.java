package issou.commun.logic.caracters;

/*
public class Minion extends CharacterAttacker{

    public static Map<Integer,Minion> minionsCreatedThisGame = new HashMap<>();

    public CardAsset ca;
    public CreatureEffect effect;
    public Player owner;

    private int _attacksForOneTurn;
    public int AttacksLeftThisTurn { get; set; };

    public bool CanAttack => TurnManager.Instance.WhoseTurn == owner && AttacksLeftThisTurn > 0;

    public CreatureLogic(Player owner, CardAsset ca)
    {
        super(ca.maxHealth,ca.attack, ca.attacksForOneTurn,ca.charge);
        this.ca = ca;
        this.owner = owner;
        if (!string.IsNullOrEmpty(ca.creatureScriptName))
        {
            effect = CreateInstance(Type.GetType(ca.creatureScriptName) ?? throw new InvalidOperationException(), owner, this, ca.specialCreatureAmount) as CreatureEffect;
            effect?.RegisterEffect();
        }
        CreaturesCreatedThisGame.Add(ID, this);
    }

    public void OnTurnStart()
    {
        AttacksLeftThisTurn = _attacksForOneTurn;
    }


    public void die()
    {
        owner.Table.Remove(this);
        new CreatureDieCommand(ID, owner).AddToQueue();
    }

    public void GoFace()
    {
        AttacksLeftThisTurn--;
        var targetHealthAfter = owner.OtherPlayer.Health - Attack;
        owner.OtherPlayer.TakeDamage(Health);
        new CreatureAttackCommand(owner.OtherPlayer.ID, ID, 0, Attack, Health, targetHealthAfter).AddToQueue();
    }

    public void AttackCreature(int uniqueCreatureID) => AttackCreature(CreaturesCreatedThisGame[uniqueCreatureID]);
    public void AttackCreature(CreatureLogic target)
    {
        AttacksLeftThisTurn--;
        var targetHealthAfter = target.Health - Attack;
        var attackerHealthAfter = Health - target.Attack;
        target.Health -= Attack;
        Health -= target.Attack;
        new CreatureAttackCommand(target.ID, ID, target.Attack, Attack, attackerHealthAfter, targetHealthAfter).AddToQueue();
    }

}
*/