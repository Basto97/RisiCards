package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class ManaPool {

    private int totalMana;
    private int currentMana;

    public ManaPool() {
        totalMana = 0;
        currentMana = 0;
    }

    private void increaseMana(int amount) {
        currentMana = Math.min(currentMana +amount, 10);
    }
    private void increaseManaMax(int amount) {
        totalMana = Math.min(totalMana +amount, 10);
    }
    private void decreaseMana(int amount)
    {
        currentMana = Math.max(currentMana +amount, 0);
    }
    private void decreaseManaMax(int amount)
    {
        totalMana = Math.max(totalMana +amount, 0);
    }
    private void resetCurrentMana()
    {
        currentMana = totalMana;
    }

    public void newTurn(){
        increaseManaMax(1);
        resetCurrentMana();
    }

    public ISFSObject toSFSObject() {
        ISFSObject obj = new SFSObject();
        obj.putInt("totalMana", totalMana);
        obj.putInt("currentMana", currentMana);
        return obj;
    }
}
