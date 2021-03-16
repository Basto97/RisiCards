using DG.Tweening;
using UnityEngine;
using static GlobalSettings;

public class OpponantHandVisual : HandVisual {
    
    public void Init(int handSize) {
        // TODO, servira pour les reconnetions
    }
    
    public void GiveACard(bool fast = false) {
        var card = Instantiate(Prefabs.Instance.cardBack, deckTransform.position, Quaternion.identity);
        AddCard(card);
        VisualStateManager visualStateManager = card.GetComponent<VisualStateManager>();
        visualStateManager.BringToFront();
        visualStateManager.Slot = 0;
        Sequence s = DOTween.Sequence();
        if (!fast) {
            s.Append(card.transform.DOMove(drawPreviewSpot.position, CardTransitionTime));
            s.AppendInterval(CardPreviewTime);
            s.Append(card.transform.DOLocalMove(slots.transforms[0].transform.localPosition, CardTransitionTime));
        } else 
            s.Append(card.transform.DOLocalMove(slots.transforms[0].transform.localPosition, CardTransitionTimeFast));
        s.OnComplete(() => {
            visualStateManager.VisualState = VisualStates.TopHand;
            visualStateManager.SetHandSortingOrder();
            Command.CommandExecutionComplete();
        });
    }
    
}
