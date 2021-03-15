using UnityEngine;
using System.Collections.Generic;
using System.Linq;
using DG.Tweening;

public class HandVisual : MonoBehaviour {

    
    public SameDistanceChildren slots;
    
    public Transform drawPreviewSpot;
    public Transform deckTransform;

    private readonly List<GameObject> _cardsInHand = new List<GameObject>();

    private bool _playerHand;

    public void Init(bool playerHand) =>  _playerHand = playerHand;
    public CardManager GetCardWithID(int id) => _cardsInHand.Select(card => card.GetComponent<CardManager>()).FirstOrDefault(ocm => ocm.card.ID == id);

    public void GivePlayerACard(Card c , bool fast = false) {
        GameObject card;
        if (_playerHand) {
            card = Instantiate(c.minion ? Prefabs.Instance.minionCard : Prefabs.Instance.spellCard, deckTransform.position, Quaternion.Euler(new Vector3(0f, -179f, 0f)));
            CardManager manager = card.GetComponent<CardManager>();
            manager.ReadCardFromAsset(c);
        }
        else {
            card = Instantiate(Prefabs.Instance.cardBack, deckTransform.position, Quaternion.Euler(new Vector3(0f, 0f, 0f)));
        }
        GiveUserACard(card, fast);
    }

    private void GiveUserACard(GameObject card, bool fast) {
        AddCard(card);
        
        VisualStateManager w = card.GetComponent<VisualStateManager>();
        w.BringToFront();
        w.Slot = 0;
        Sequence s = DOTween.Sequence();
        if (!fast) {
            s.Append(card.transform.DOMove(drawPreviewSpot.position, GlobalSettings.CardTransitionTime));
            s.Insert(0f, _playerHand
                ? card.transform.DORotate(Vector3.zero, GlobalSettings.CardTransitionTime)
                : card.transform.DORotate(card.transform.eulerAngles, GlobalSettings.CardTransitionTime));
            s.AppendInterval(GlobalSettings.CardPreviewTime);
            s.Append(card.transform.DOLocalMove(slots.children[0].transform.localPosition, GlobalSettings.CardTransitionTime));
        } else {
            s.Append(card.transform.DOLocalMove(slots.children[0].transform.localPosition, GlobalSettings.CardTransitionTimeFast));
            if (_playerHand)    
                s.Insert(0f,card.transform.DORotate(Vector3.zero, GlobalSettings.CardTransitionTimeFast)); 
        }
        s.OnComplete(() => {
            w.VisualState = _playerHand ? VisualStates.LowHand : VisualStates.TopHand;
            w.SetHandSortingOrder();
            Command.CommandExecutionComplete();
        });
    }

    // UTILS
    
    public void AddCard(GameObject card) {
        _cardsInHand.Insert(0, card);
        card.transform.SetParent(slots.transform);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }
    
    public void RemoveCard(GameObject card) {
        _cardsInHand.Remove(card);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }
    
    public void RemoveCardAtIndex(int index) {
        _cardsInHand.RemoveAt(index);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }

    private void UpdatePlacementOfSlots() {
        float posX = _cardsInHand.Count > 0
            ? (slots.children[0].transform.localPosition.x - slots.children[_cardsInHand.Count - 1].transform.localPosition.x) / 2f
            : 0f;
        slots.gameObject.transform.DOLocalMoveX(posX, 0.3f);
    }
    
    private void PlaceCardsOnNewSlots() {
        foreach (GameObject g in _cardsInHand) {
            g.transform.DOLocalMoveX(slots.children[_cardsInHand.IndexOf(g)].transform.localPosition.x, 0.3f);
            VisualStateManager w = g.GetComponent<VisualStateManager>();
            w.Slot = _cardsInHand.IndexOf(g);
            w.SetHandSortingOrder();
        }
    }

    /*
    public void PlayASpellFromHand(GameObject cardVisual) {
        Command.CommandExecutionComplete();
        cardVisual.GetComponent<ObjectVisualState>().VisualState = VisualStates.Transition;
        RemoveCard(cardVisual);

        cardVisual.transform.SetParent(null);

        Sequence s = DOTween.Sequence();
        s.Append(cardVisual.transform.DOMove(playPreviewSpot.position, 1f));
        s.Insert(0f, cardVisual.transform.DORotate(Vector3.zero, 1f));
        s.AppendInterval(2f);
        s.OnComplete(()=>
            {
                Command.CommandExecutionComplete();
                Destroy(cardVisual);
            });
    }*/
}
