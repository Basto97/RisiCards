package issou.commun.logic.objects.manapool;

public class ManaPool implements  IManaPool {

    private int _totalMana;
    private int _currentMana;

    public ManaPool(int startMana) {
        set(startMana);
    }

    @Override
    public boolean hasEnough(int amount) {
        return  _currentMana >= amount;
    }
    @Override
    public int getMana() {
        return _currentMana;
    }
    @Override
    public int getManaMax() {
        return _totalMana;
    }
    @Override
    public void set(int amount){
        _totalMana = _currentMana = amount;
    }
    @Override
    public void increaseMana(int amount)
    {
        _currentMana = Math.min(_currentMana+amount, 10);
    }
    @Override
    public void increaseManaMax(int amount)
    {
        _totalMana = Math.min(_totalMana+amount, 10 /*Constantes.MANA_MAX*/);
    }
    @Override
    public void decreaseMana(int amount)
    {
        _currentMana = Math.max(_currentMana+amount, 0);
    }
    @Override
    public void decreaseManaMax(int amount)
    {
        _totalMana = Math.max(_totalMana+amount, 0);
    }
    @Override
    public void resetCurrentMana()
    {
        _currentMana = _totalMana;
    }

    @Override
    public String toString() {
        return "ManaPool{" +
                "_totalMana=" + _totalMana +
                ", _currentMana=" + _currentMana +
                '}';
    }
}
