public class GameAPI {

        public GameState state;

        public void StartGame(GameState ga) {
                state = ga;
        }
        
        public void OnDraw(Card drawed) {
                state.Player.DeckSize--;
                state.Player.Hand.AddCard(drawed);
        }

        public void OnOpponantDraw() {
                state.Opponant.DeckSize--;
                state.Opponant.HandSize++;
        }

        public void NewTurn(bool playerTurn, float timeToplay, Pool newPool) {
                state.TimeToPlay = timeToplay;
                if (playerTurn)
                        state.Player.Pool = newPool;
                else 
                        state.Opponant.Pool = newPool;
                new StartATurnCommand(state, playerTurn).AddToQueue();
        }
}