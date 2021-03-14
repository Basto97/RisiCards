using Sfs2X.Entities.Data;

public abstract class Identifiable {
    public int ID { get; }

    protected Identifiable(ISFSObject obj) {
        ID = obj.GetInt("id");
    }

    protected bool Equals(Identifiable other) {
        return ID == other.ID;
    }

    public override bool Equals(object obj) {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((Identifiable) obj);
    }

    public override int GetHashCode() {
        return ID;
    }
}