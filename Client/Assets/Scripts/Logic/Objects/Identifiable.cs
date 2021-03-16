using Sfs2X.Entities.Data;

public abstract class Identifiable {
    private static int _clientIdMaker;
    public int ID { get; }

    protected Identifiable(ISFSObject obj) {
        ID = obj.GetInt("id");
    }

    protected Identifiable() {
        ID = _clientIdMaker++;
    }

    protected bool Equals(Identifiable other) {
        return ID == other.ID;
    }

    public override bool Equals(object obj) {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        return obj.GetType() == this.GetType() && Equals((Identifiable) obj);
    }

    public override int GetHashCode() {
        return ID;
    }
}