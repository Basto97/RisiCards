package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class ManaPool {

    public int totalMana;
    public int currentMana;

    public ManaPool(int startMana) {
        totalMana = startMana;
        currentMana = startMana;
    }

    public void increaseMana(int amount)
    {
        currentMana = Math.min(currentMana +amount, 10);
    }
    public void increaseManaMax(int amount)
    {
        totalMana = Math.min(totalMana +amount, 10 /*Constantes.MANA_MAX*/);
    }
    public void decreaseMana(int amount)
    {
        currentMana = Math.max(currentMana +amount, 0);
    }
    public void decreaseManaMax(int amount)
    {
        totalMana = Math.max(totalMana +amount, 0);
    }
    public void resetCurrentMana()
    {
        currentMana = totalMana;
    }


    public ISFSObject toSFSObject() {
        ISFSObject obj = new SFSObject();
        obj.putInt("totalMana", totalMana);
        obj.putInt("currentMana", currentMana);
        return obj;
    }
}
