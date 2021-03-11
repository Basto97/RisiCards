using System;
using Sfs2X.Entities.Data;

[Serializable]
public class Card : Identifiable {
    
    public string name;
    public int cost { get; }
   
    
    public Card(ISFSObject obj) : base(obj){
        name = obj.GetUtfString("name");
        cost = obj.GetInt("cost");
    }
        
}