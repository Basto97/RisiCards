using Sfs2X.Entities.Data;

public abstract class Identifiable {
    public int ID;

    protected Identifiable(ISFSObject obj) {
        ID = obj.GetInt("id");
    }
}