package issou.logic.objects;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class ManaPool {

    private int _totalMana;
    private int _currentMana;

    public ManaPool(int startMana) {
        set(startMana);
    }

    public boolean hasEnough(int amount) {
        return  _currentMana >= amount;
    }
    public int getMana() {
        return _currentMana;
    }
    public int getManaMax() {
        return _totalMana;
    }
    public void set(int amount){
        _totalMana = _currentMana = amount;
    }
    public void increaseMana(int amount)
    {
        _currentMana = Math.min(_currentMana+amount, 10);
    }
    public void increaseManaMax(int amount)
    {
        _totalMana = Math.min(_totalMana+amount, 10 /*Constantes.MANA_MAX*/);
    }
    public void decreaseMana(int amount)
    {
        _currentMana = Math.max(_currentMana+amount, 0);
    }
    public void decreaseManaMax(int amount)
    {
        _totalMana = Math.max(_totalMana+amount, 0);
    }
    public void resetCurrentMana()
    {
        _currentMana = _totalMana;
    }


    public ISFSObject toSFSObject() {
        ISFSObject obj = new SFSObject();
        obj.putInt("totalMana", _totalMana);
        obj.putInt("currentMana", _currentMana);
        return obj;
    }
}
