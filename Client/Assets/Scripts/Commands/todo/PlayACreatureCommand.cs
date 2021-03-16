public class PlayACreatureCommand : Command
{
    
    private Card c;
    private int _tablePos;
    private Player _p;
    private int _creatureID;

    public PlayACreatureCommand(Card c, Player p, int tablePos, int creatureID)
    {
        this._p = p;
        this.c = c;
        this._tablePos = tablePos;
        this._creatureID = creatureID;
    }

    protected override void StartCommandExecution() {
        // remove and destroy the card in hand 
        //HandVisual playerHand = _p.PArea.handVisual;
       // GameObject card = IDHolder.GetGameObjectWithID(_cl.UniqueCardID);
        //playerHand.RemoveCard(card);
       // Object.Destroy(card);
        // enable Hover Previews Back
        //HoverPreview.PreviewsAllowed = true;
        // move this card to the spot 
        //_p.PArea.tableVisual.AddCreatureAtIndex(_cl.ca, _creatureID, _tablePos);
    }
}
