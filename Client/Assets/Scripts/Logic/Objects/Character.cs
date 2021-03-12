 using Sfs2X.Entities.Data;

 public abstract class Character : Identifiable {
     
     public readonly int healthMax;
     public int health;
     public int healthBonus;
     public readonly int attack;
     public int attackBonus;
     public int attackBonusTurn;
     public int attacksPerTurn;
     public int attacksLeftThisTurn;
     
     protected Character(ISFSObject obj) : base(obj) {
         healthMax  = obj.GetInt("healthMax");
         health = obj.GetInt("health");
         healthBonus = obj.GetInt("healthBonus");
         attack = obj.GetInt("attack");
         attackBonus = obj.GetInt("attackBonus");
         attackBonusTurn = obj.GetInt("attackBonusTurn");
         attacksPerTurn = obj.GetInt("attacksPerTurn");
         attacksLeftThisTurn = obj.GetInt("attacksLeftThisTurn");
     }
 }
