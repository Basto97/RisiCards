using System;
using Sfs2X.Entities.Data;

[Serializable]
public class Card : Identifiable {
    
    public string Name { get; }
    public bool minion;
    public int[] special;
    public string[] effects;
    public int baseCost;
    public int costModification;
    public string hero;

    public int attack;
    public int attackModification;
    public int health;
    public int healthModification;
    public string type;
   
    public string targetType;

    public int Cost => baseCost + costModification;

    public Card(ISFSObject obj) : base(obj){
        Name = obj.GetUtfString("name");
        minion = obj.GetBool("minion");
        baseCost = obj.GetInt("cost");
        costModification = obj.GetInt("costModification");
        special = obj.GetIntArray("special");
        effects = obj.GetUtfStringArray("effects");
        hero = obj.GetUtfString("hero");
        if (minion) {
            attack = obj.GetInt("attack");
            attackModification = obj.GetInt("attackModification");
            health = obj.GetInt("health");
            healthModification = obj.GetInt("healthModification");
            type = obj.GetUtfString("type");
        }
        else {
            targetType = obj.GetUtfString("tagetType");
        }
    }
}