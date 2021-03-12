public abstract class DrawACardCommand : Command {
    
    private Card _cl;

    public DrawACardCommand(Card card) {        
        _cl = card;
    }

    protected override void StartCommandExecution() {
        // _p.PArea.PDeck.CardsInDeck--;
        // _p.PArea.handVisual.GivePlayerACard(_cl.ca, _cl.UniqueCardID, _handPos, _fast, _fromDeck);
    }
}
