using Sfs2X.Entities.Data;

public class Player : UserState {
    public Hand Hand { get; }

    public Player(ISFSObject player, ISFSObject hand) : base(player) {
        Hand = new Hand(hand);
    }

    public bool CanPlayThisCard(Card c) {
        if (!Hand.Contains(c)) return false;
        return Pool.currentMana > c.Cost;
    }
}