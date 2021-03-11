using System;
using Sfs2X.Entities.Data;

[Serializable]
public class Hero {
    public string name;
    
    public Hero(ISFSObject obj) {
        name = obj.GetUtfString("name");
    }
}