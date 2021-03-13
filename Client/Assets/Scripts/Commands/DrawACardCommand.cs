public class DrawACardCommand : Command {
    
    private readonly Card _cl;
    private readonly HandVisual _handVisual;

    public DrawACardCommand(Card card, HandVisual handVisual) {
        _cl = card;
        _handVisual = handVisual;
    }
    
    protected override void StartCommandExecution() {
        _handVisual.GivePlayerACard(_cl);
    }
}
