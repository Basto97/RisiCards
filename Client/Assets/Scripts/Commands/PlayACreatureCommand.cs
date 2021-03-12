using UnityEngine;

public class PlayACreatureCommand : Command
{
    /*
    private CardLogic _cl;
    private int _tablePos;
    private Player _p;
    private int _creatureID;

    public PlayACreatureCommand(CardLogic cl, Player p, int tablePos, int creatureID)
    {
        this._p = p;
        this._cl = cl;
        this._tablePos = tablePos;
        this._creatureID = creatureID;
    }

    protected override void StartCommandExecution()
    {
        // remove and destroy the card in hand 
        HandVisual playerHand = _p.PArea.handVisual;
        GameObject card = IDHolder.GetGameObjectWithID(_cl.UniqueCardID);
        playerHand.RemoveCard(card);
        Object.Destroy(card);
        // enable Hover Previews Back
        HoverPreview.PreviewsAllowed = true;
        // move this card to the spot 
        _p.PArea.tableVisual.AddCreatureAtIndex(_cl.ca, _creatureID, _tablePos);
    }*/
}
