using System;
using Sfs2X.Entities.Data;

[Serializable]
public class Card : Identifiable {
    
    public string name;
    public bool minion;
    public int[] special;
    public string[] effects;
    public int cost;
    public int costModification;

    public int attack;
    public int attackModification;
    public int health;
    public int healthModification;
    public string type;
   
    public string targetType;

    public Card(ISFSObject obj) : base(obj){
        name = obj.GetUtfString("name");
        minion = obj.GetBool("minion");
        cost = obj.GetInt("cost");
        costModification = obj.GetInt("costModification");
        special = obj.GetIntArray("special");
        effects = obj.GetUtfStringArray("effects");

        attack = obj.GetInt("attack");
        attackModification = obj.GetInt("attackModification");
        health = obj.GetInt("health");
        healthModification = obj.GetInt("healthModification");
        type = obj.GetUtfString("type");

        targetType = obj.GetUtfString("tagetType");
    }
        
}