using System.Linq;
using UnityEngine;
using DG.Tweening;
using static GlobalSettings;

public class PlayerHandVisual : HandVisual {

    public void GiveACard(Card c , bool fast = false) {
        var card = Instantiate(c.minion ? Prefabs.Instance.minionCard : Prefabs.Instance.spellCard, deckTransform.position, Quaternion.Euler(new Vector3(0f, -179f, 0f)));
        card.GetComponent<CardManager>().ReadCard(c);
        AddCard(card);
        VisualStateManager visualStateManager = card.GetComponent<VisualStateManager>();
        visualStateManager.BringToFront();
        visualStateManager.Slot = 0;
        Sequence s = DOTween.Sequence();
        if (!fast) {
            s.Append(card.transform.DOMove(drawPreviewSpot.position, CardTransitionTime));
            s.Insert(0f, card.transform.DORotate(Vector3.zero, CardTransitionTime));
            s.AppendInterval(CardPreviewTime);
            s.Append(card.transform.DOLocalMove(slots.transforms[0].transform.localPosition, CardTransitionTime));
        } else {
            s.Append(card.transform.DOLocalMove(slots.transforms[0].transform.localPosition, CardTransitionTimeFast));
            s.Insert(0f,card.transform.DORotate(Vector3.zero, CardTransitionTimeFast)); 
        }
        s.OnComplete(() => {
            visualStateManager.VisualState = VisualStates.LowHand;
            visualStateManager.SetHandSortingOrder();
            Command.CommandExecutionComplete();
        });
    }

    public void HighlightPlayable(Player player) {
        foreach (var cm in cardsInHand.Select(o => o.GetComponent<CardManager>()))
            cm.CanBePlayed = player.CanPlayThisCard(cm.Card);
    }

    public void StopHighlight() {
        foreach (var cm in cardsInHand.Select(o => o.GetComponent<CardManager>()))
            cm.CanBePlayed = false;
    }

    public void Init(Hand psHand) {
        // TODO, servira pour les reconnetions
    }
}
