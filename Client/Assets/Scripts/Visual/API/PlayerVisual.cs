public class PlayerVisual : UserVisual {
    public PlayerHandVisual hand;
    
    public void Init(Player ps) {
        base.Init(ps);
        hand.Init(ps.Hand);
    }
}