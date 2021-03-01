package issou.logic.objects;

public class ManaPool {

    private int _totalMana;
    private int _currentMana;

    public ManaPool(int startMana) {
        set(startMana);
    }

    public boolean hasEnough(int amount) {
        return  _currentMana >= amount;
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
}
