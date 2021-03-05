package issou.commun.logic.utils;

public abstract class Identifiable implements IIdentifiable {

    private static int actualId = 1;
    private final int id;

    public Identifiable(){
        this.id = actualId++;
    }

    public int getId() {
        return this.id;
    }
}
