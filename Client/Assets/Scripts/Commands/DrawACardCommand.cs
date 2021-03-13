public class DrawACardCommand : Command {
    
    private readonly Card _cl;
    private readonly DeckVisual _deckVisual;
    private readonly HandVisual _handVisual;
    private readonly int _newDeckSize;

    public DrawACardCommand(Card card, int newDeckSize, HandVisual handVisual, DeckVisual deckVisual) {
        _cl = card;
        _handVisual = handVisual;
        _deckVisual = deckVisual;
        _newDeckSize = newDeckSize;
    }
    
    protected override void StartCommandExecution() {
        _handVisual.GivePlayerACard(_cl);
        _deckVisual.UpdateSize(_newDeckSize);
    }
}
