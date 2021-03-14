using Sfs2X.Entities.Data;
using UnityEngine;
using UnityEngine.Serialization;

public class GameAPI : MonoBehaviour {
    public static GameAPI instance;
    public State gameState;

    public PlayersVisual playersVisual;
    public RopeVisual ropeVisual;

    private void Awake() => instance = this;
    
    public void HighLighPlayable() {
        foreach (var c in gameState.handPlayer.cards) {
            c.canBePlayed =  c.cost + c.costModification <= gameState.player.manaPool.currentMana
            playersVisual.Player.handVisual.GetCardWithID(c.ID).CanBePlayedNow =;
        }
    }
    
    // CLIENT CALLS

    public void EndTurn() {
        SmartFoxConnection.Send("endTurn");
    }

    // SERVER CALLS
    
    public void NewGame(SFSObject publicState) {
        gameState = new State(publicState);
        playersVisual.Player.Init(gameState.player);
        playersVisual.Opponant.Init(gameState.opponnant);
    }

    public void Draw(SFSObject obj) {
        int newDeckSize = obj.GetInt("newDeckSize");
        if (obj.GetInt("user").Equals(SmartFoxConnection.sfs.MySelf.Id)) {
            gameState.player.deckSize--;
            gameState.player.handSize++;
            Card drawed = new Card(obj.GetSFSObject("card"));
            gameState.handPlayer.AddCard(drawed);
            new DrawACardCommand(drawed, newDeckSize,  playersVisual.Player.handVisual,  playersVisual.Player.deckVisual).AddToQueue();
        } else {
            gameState.opponnant.deckSize--;
            gameState.opponnant.handSize++;
            new DrawACardCommand(null,newDeckSize, playersVisual.Opponant.handVisual, playersVisual.Opponant.deckVisual).AddToQueue();
        }
            
    }

    public void NewTurn(SFSObject obj) {
        gameState.myTurn = obj.GetInt("user").Equals(SmartFoxConnection.sfs.MySelf.Id);
        
        ropeVisual.OnNewTurn(gameState.myTurn, obj.GetFloat("time"));

        ManaPool pool = new ManaPool(obj.GetSFSObject("pool"));
        gameState.userPlaying.manaPool = pool;
        playersVisual.UserPlaying.poolVisual.UpdatePoolVisual(pool);
        
        new ShowMessageCommand(gameState.myTurn ? "Votre tour." : "Tour de l'adversaire", 1.5f).AddToQueue();
    }

}