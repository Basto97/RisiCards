using UnityEngine;

public class VisualAPI : MonoBehaviour {
        
        [SerializeField] private UserVisual playerVisual;
        [SerializeField] private UserVisual opponantVisual;
        [SerializeField] private RopeVisual ropeVisual;
        
        public void StartGame(GameState gameState) {
                playerVisual.Init(gameState.Player, true);
                opponantVisual.Init(gameState.Opponant, false);
        }

        public void Draw(Card drawed, int newDeckSize) =>  new DrawACardCommand(drawed, newDeckSize,  playerVisual.handVisual,  playerVisual.deckVisual).AddToQueue();
        public void OpponantDraw(int newDeckSize) =>  new DrawACardCommand(null, newDeckSize, opponantVisual.handVisual, opponantVisual.deckVisual).AddToQueue();
        public void NewTurn(bool playerTurn, float timeToPlay, Pool newPool) {
                new ShowMessageCommand(playerTurn ? "Votre tour" : "Tour de l'adversaire", 1.5f).AddToQueue();
                new UpdatePoolCommand(newPool, (playerTurn ? playerVisual: opponantVisual).poolVisual).AddToQueue();
                new UpdateRopeCommand(ropeVisual, playerTurn, timeToPlay).AddToQueue();
        }
        public void HighlightPlayableCards(Player player) {
                foreach (Card c in player.Hand.Cards) 
                        playerVisual.handVisual.GetCardWithID(c.ID).CanBePlayedNow = player.CanPlayThisCard(c);
        }
}
