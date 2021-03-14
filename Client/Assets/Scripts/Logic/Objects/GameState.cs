using Sfs2X.Entities.Data;

public enum State {
    Playing,
    Finished
}

public class GameState {
    public Player Player { get; }
    public Opponant Opponant { get; }
    public bool MyTurn { get; set; }
    public float TimeToPlay { get; set; }
    public State State { get; }

    public GameState(ISFSObject state) {
        Player = new Player(state.GetSFSObject("player"));
        Opponant = new Opponant(state.GetSFSObject("opponant"));
        MyTurn = state.GetBool("first");
        State = State.Playing;
    }

    public UserState UserPlaying => MyTurn ? (UserState) Player : Opponant;
}

public abstract class UserState {
    public Hero Hero { get; }
    public HeroPower HeroPower { get; }
    public Pool Pool { get; set; }
    public int DeckSize { get; set; }

    protected UserState(ISFSObject obj) {
        Hero = new Hero(obj.GetSFSObject("hero"));
        HeroPower = new HeroPower(obj.GetSFSObject("heroPower"));
        Pool = new Pool(obj.GetSFSObject("pool"));
        DeckSize = obj.GetInt("deckSize");
    }
}

public class Opponant : UserState {
    public int HandSize { get; set; }

    public Opponant(ISFSObject obj) : base(obj) {
        HandSize = obj.GetInt("handSize");
    }
}