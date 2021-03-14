using Sfs2X.Entities.Data;

public class State {
    
    public OpponantState opponnant;
    public PlayerState player;
    public bool myTurn;

    public State(ISFSObject state) {
        
    }

    public PlayerState userPlaying => myTurn ? player : opponnant;
}

public class PlayerState {
    public Hero hero;
    public HeroPower heroPower;
    public ManaPool manaPool;
    public Hand handPlayer; 
    public int deckSize;

    public PlayerState(ISFSObject obj) {
        hero = new Hero(obj.GetSFSObject("hero"));
        heroPower = new HeroPower(obj.GetSFSObject("heroPower"));
        manaPool = new ManaPool(obj.GetSFSObject("manaPool"));
        deckSize = obj.GetInt("deckSize");
        handPlayer = new Hand(obj.GetSFSObject("hand"));
    }
}

public class OpponantState {
    public Hero hero;
    public HeroPower heroPower;
    public ManaPool manaPool;
    public int handSize;
    public int deckSize;

    public OpponantState(ISFSObject obj) {
        hero = new Hero(obj.GetSFSObject("hero"));
        heroPower = new HeroPower(obj.GetSFSObject("heroPower"));
        manaPool = new ManaPool(obj.GetSFSObject("manaPool"));
        handSize = obj.GetInt("handSize");
        deckSize = obj.GetInt("deckSize");
    }
}