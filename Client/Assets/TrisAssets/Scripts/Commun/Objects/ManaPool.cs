using System;
using Sfs2X.Entities.Data;

[Serializable]
public class ManaPool {
    public int totalMana;
    public int currentMana;

    public ManaPool(ISFSObject obj) {
        totalMana = obj.GetInt("totalMana");
        currentMana = obj.GetInt("currentMana");
    }
    
}