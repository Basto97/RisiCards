using System;
using Sfs2X.Entities.Data;

[Serializable]
public class Hero : Character {
    public string name;
    
    public Hero(ISFSObject obj) : base(obj) {
        name = obj.GetUtfString("name");
    }
}