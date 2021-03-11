﻿using System;
using Sfs2X.Entities.Data;

[Serializable]
public class HeroPower {
    public string name;
    public int cost;
    public int costModification;
    
    public HeroPower(ISFSObject obj) {
        name = obj.GetUtfString("name");
        cost = obj.GetInt("cost");
        costModification = obj.GetInt("costModification");
    }
}
