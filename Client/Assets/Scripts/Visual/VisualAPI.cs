using UnityEngine;

public class VisualAPI : MonoBehaviour {
        
        [SerializeField] private PlayerVisual playerVisual;
        [SerializeField] private OpponantVisual opponantVisual;
        [SerializeField] private RopeVisual ropeVisual;

        public void StartGame(GameState gameState) {
                playerVisual.Init(gameState.Player);
                opponantVisual.Init(gameState.Opponant);
        }

        public void Draw(Card drawed, int newDeckSize) =>  new DrawACardCommand(playerVisual.hand, playerVisual.deckVisual, newDeckSize, drawed).AddToQueue();
        public void OpponantDraw(int newDeckSize) =>  new DrawACardCommand(opponantVisual.hand, opponantVisual.deckVisual, newDeckSize).AddToQueue();
        
        public void NewTurn(bool playerTurn, float timeToPlay, Pool newPool) {
                new ShowMessageCommand(playerTurn ? "Votre tour" : "Tour de l'adversaire", 1.5f).AddToQueue();
                new UpdatePoolCommand(newPool, playerTurn ? playerVisual.poolVisual : opponantVisual.poolVisual).AddToQueue();
                new UpdateRopeCommand(ropeVisual, playerTurn, timeToPlay).AddToQueue();
        }


        public void HighlighPlayable(bool playerTurn) {
                if (playerTurn) return;
                
        }
        
}
