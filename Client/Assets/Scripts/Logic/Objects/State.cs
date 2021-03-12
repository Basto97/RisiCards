using System;
using Sfs2X.Entities.Data;
using UnityEngine;

public class State {
    public PlayerState opponnant;
    public PlayerState player;
    public Hand handPlayer;

    public State(ISFSObject publicState) {
        ISFSArray publicStates = publicState.GetSFSArray("players");
        for (int i = 0; i < publicStates.Size(); i++) {
            ISFSObject playerState = publicStates.GetSFSObject(i);
            if (playerState.GetUtfString("player") == SmartFoxConnection.sfs.MySelf.Name)
                player = new PlayerState(playerState);
            else 
                opponnant = new PlayerState(playerState);
        }
    }
}

public class PlayerState {
    public Hero hero;
    public HeroPower heroPower;
    public ManaPool manaPool;
    public int deckSize;
    public int handSize;

    public PlayerState(ISFSObject obj) {
        hero = new Hero(obj.GetSFSObject("hero"));
        
        heroPower = new HeroPower(obj.GetSFSObject("heroPower"));
        manaPool = new ManaPool(obj.GetSFSObject("manaPool"));
        deckSize = obj.GetInt("deckSize");
        handSize = obj.GetInt("handSize");
    }
}