using System;
using UnityEngine;

public class VisualAPI : MonoBehaviour {
        
        [SerializeField] private PlayerVisual player;
        [SerializeField] private PlayerVisual opponant;
        [SerializeField] private RopeVisual ropeVisual;

        private PlayerVisual userPlaying => _gameState.MyTurn ? player : opponant;
        private GameState _gameState;

        public void Init(GameState gameState) {
                _gameState = gameState;
                player.Init(_gameState.Player);
                opponant.Init(_gameState.Opponant);
        }

        public void Draw() =>  new DrawACardCommand(_gameState.Player.Hand.LastDrawedCard, _gameState.Player.DeckSize,  player.handVisual,  player.deckVisual).AddToQueue();
        public void OpponantDraw() =>  new DrawACardCommand(null, _gameState.Opponant.DeckSize, opponant.handVisual, player.deckVisual).AddToQueue();
        public void NewTurn() {
                ropeVisual.OnNewTurn(_gameState.MyTurn, _gameState.TimeToPlay);
                userPlaying.poolVisual.UpdatePoolVisual(_gameState.UserPlaying.Pool);
                new ShowMessageCommand(_gameState.MyTurn ? "Votre tour" : "Tour de l'adversaire", 1.5f).AddToQueue();
        }
        
        public void HighlightPlayableCards() {
                foreach (Card c in _gameState.Player.Hand.Cards) 
                        player.handVisual.GetCardWithID(c.ID).CanBePlayedNow = _gameState.Player.CanPlayThisCard(c);
        }
}
