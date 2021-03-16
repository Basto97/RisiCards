public class OpponantVisual : UserVisual {

        public OpponantHandVisual hand;

        public void Init(Opponant opponant) {
                base.Init(opponant);
                hand.Init(opponant.HandSize);
        }
}