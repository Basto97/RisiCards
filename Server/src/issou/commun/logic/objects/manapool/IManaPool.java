package issou.commun.logic.objects.manapool;

public interface IManaPool {

    boolean hasEnough(int amount);
    int getMana();
    int getManaMax();
    void set(int amount);
    void increaseMana(int amount);
    void increaseManaMax(int amount);
    void decreaseMana(int amount);
    void decreaseManaMax(int amount);
    void resetCurrentMana();
}
