using Sfs2X.Entities.Data;

public class Opponant : UserState {
    
    public int HandSize { get; set; }

    public Opponant(ISFSObject obj) : base(obj) {
        HandSize = obj.GetInt("handSize");
    }
}