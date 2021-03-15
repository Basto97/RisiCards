public class PlayASpellCardCommand: Command
{
    private Card c;
    private Player _p;
    //private ICharacter target;

    public PlayASpellCardCommand(Player p, Card c)
    {
        this.c = c;
        this._p = p;
    }

    protected override void StartCommandExecution()
    {
        // move this card to the spot
        // _p.PArea.handVisual.PlayASpellFromHand(_card.UniqueCardID);
        // do all the visual stuff (for each spell separately????)
    }
}
