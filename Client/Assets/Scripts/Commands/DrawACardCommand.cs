public class DrawACardCommand : Command {
    
    private readonly DeckVisual _deckVisual;
    private readonly int _newDeckSize;
    
    private readonly PlayerHandVisual _playerHandVisual;
    private readonly Card _card;

    private readonly OpponantHandVisual _opponantHandVisual;
    
    private DrawACardCommand(int newDeckSize, DeckVisual deckVisual) {
        _deckVisual = deckVisual;
        _newDeckSize = newDeckSize;
    }
    
    public DrawACardCommand(PlayerHandVisual playerHandVisual, DeckVisual deckVisual, int newDeckSize, Card card) : this(newDeckSize, deckVisual) {
        _playerHandVisual = playerHandVisual;
        _card = card;
    }
    
    public DrawACardCommand(OpponantHandVisual opponantHandVisual, DeckVisual deckVisual, int newDeckSize) : this(newDeckSize, deckVisual) {
        _opponantHandVisual = opponantHandVisual;
    }
    
    protected override void StartCommandExecution() {
        _deckVisual.UpdateSize(_newDeckSize);
        if (_playerHandVisual != null) 
            _playerHandVisual.GiveACard(_card);
        else
            _opponantHandVisual.GiveACard();        
    }
}
