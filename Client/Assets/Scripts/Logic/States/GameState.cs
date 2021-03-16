using Sfs2X.Entities.Data;

public class GameState {
    public Player Player { get; }
    public Opponant Opponant { get; }
    public bool PlayerTurn { get; set; }
    public float TimeToPlay { get; set; }

    public GameState(ISFSObject state) {
        Player = new Player(state.GetSFSObject("player"), state.GetSFSObject("hand"));
        Opponant = new Opponant(state.GetSFSObject("opponant"));
        PlayerTurn = state.GetBool("playerTurn");
    }

    public UserState UserPlaying => PlayerTurn ? (UserState) Player : Opponant;
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