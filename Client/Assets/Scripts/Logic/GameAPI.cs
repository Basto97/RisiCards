public class GameAPI {

        public GameState State;

        public void StartGame(GameState ga) {
                State = ga;
        }
        
        public void draw(Card drawed) {
                State.Player.DeckSize--;
                State.Player.Hand.AddCard(drawed);
        }

        public void opponantDraw() {
                State.Opponant.DeckSize--;
                State.Opponant.HandSize++;
        }

        public void NewTurn(bool playerTurn, float timeToplay, Pool newPool) {
                State.TimeToPlay = timeToplay;
                if (playerTurn)
                        State.Player.Pool = newPool;
                else 
                        State.Opponant.Pool = newPool;
                new StartATurnCommand(State, playerTurn).AddToQueue();
        }

        public bool WaitingUserPlay => true;
}