using System;
using Sfs2X.Entities.Data;

[Serializable]
public class State {
    public PublicState stateOpponant;
    public PublicState statePlayer;
    public Hand handPlayer;

    public State(ISFSObject privateState) {
        handPlayer = new Hand(privateState.GetSFSArray("hand"));
        ISFSArray publicStates = privateState.GetSFSArray("publicStates");
        for (int i = 0; i < publicStates.Size(); i++) {
            ISFSObject publicState = publicStates.GetSFSObject(i);
            if (publicState.GetUtfString("player") == SmartFoxConnection.sfs.MySelf.Name)
                statePlayer = new PublicState(publicState);
            else 
                stateOpponant = new PublicState(publicState);
        }
    }
}

[Serializable]
public class PublicState {
    public Hero hero;
    public HeroPower heroPower;
    public ManaPool manaPool;
    public int deckSize;
    public int handSize;

    public PublicState(ISFSObject obj) {
        hero = new Hero(obj.GetSFSObject("hero"));
        heroPower = new HeroPower(obj.GetSFSObject("heroPower"));
        manaPool = new ManaPool(obj.GetSFSObject("manaPool"));
        deckSize = obj.GetInt("deckSize");
        handSize = obj.GetInt("handSize");
    }
}