using System;
using Sfs2X.Entities.Data;

[Serializable]
public class Pool {
    public int totalMana;
    public int currentMana;

    public Pool(ISFSObject obj) {
        totalMana = obj.GetInt("totalMana");
        currentMana = obj.GetInt("currentMana");
    }
    
}