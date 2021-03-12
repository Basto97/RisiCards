using UnityEngine;
using System.Collections.Generic;
using DG.Tweening;

public class HandVisual : MonoBehaviour {

    public string owner = "Player";
    
    public bool takeCardsOpenly = true;
    public SameDistanceChildren slots;
    
    public Transform drawPreviewSpot;
    public Transform deckTransform;

    private readonly List<GameObject> _cardsInHand = new List<GameObject>();
    
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
    
    public GameObject GetCardAtIndex(int index) => _cardsInHand[index];

    private void UpdatePlacementOfSlots() {
        float posX = _cardsInHand.Count > 0
            ? (slots.Children[0].transform.localPosition.x - slots.Children[_cardsInHand.Count - 1].transform.localPosition.x) / 2f
            : 0f;
        slots.gameObject.transform.DOLocalMoveX(posX, 0.3f);
    }
    
    private void PlaceCardsOnNewSlots() {
        foreach (GameObject g in _cardsInHand) {
            g.transform.DOLocalMoveX(slots.Children[_cardsInHand.IndexOf(g)].transform.localPosition.x, 0.3f);
            ObjectVisualState w = g.GetComponent<ObjectVisualState>();
            w.Slot = _cardsInHand.IndexOf(g);
            w.SetHandSortingOrder();
        }
    }

    private static GameObject CreateACardAtPosition(Card c, Vector3 position, Vector3 eulerAngles) {
        var card = Instantiate(c.minion ? Prefabs.Instance.minionCard : Prefabs.Instance.spellCard, position, Quaternion.Euler(eulerAngles));
        OneCardManager manager = card.GetComponent<OneCardManager>();
        manager.card = c;
        manager.ReadCardFromAsset();
        return card;
    }
    
    public void GivePlayerACard(Card c , bool fast = false) {
        var card = CreateACardAtPosition(c, deckTransform.position , new Vector3(0f, -179f, 0f));
        foreach (Transform t in card.GetComponentsInChildren<Transform>())
            t.tag = owner+"Card";
        AddCard(card);
        
        ObjectVisualState w = card.GetComponent<ObjectVisualState>();
        w.BringToFront();
        w.Slot = 0;

        Sequence s = DOTween.Sequence();
        if (!fast) {
            s.Append(card.transform.DOMove(drawPreviewSpot.position, GlobalSettings.CardTransitionTime));
            s.Insert(0f, takeCardsOpenly
                    ? card.transform.DORotate(Vector3.zero, GlobalSettings.CardTransitionTime)
                    : card.transform.DORotate(new Vector3(0f, 179f, 0f), GlobalSettings.CardTransitionTime));
            s.AppendInterval(GlobalSettings.CardPreviewTime);
            s.Append(card.transform.DOLocalMove(slots.Children[0].transform.localPosition, GlobalSettings.CardTransitionTime));
        }else {
            s.Append(card.transform.DOLocalMove(slots.Children[0].transform.localPosition, GlobalSettings.CardTransitionTimeFast));
            if (takeCardsOpenly)    
                s.Insert(0f,card.transform.DORotate(Vector3.zero, GlobalSettings.CardTransitionTimeFast)); 
        }
        s.OnComplete(()=>ChangeLastCardStatusToInHand(card, w));
    }

    private void ChangeLastCardStatusToInHand(GameObject card, ObjectVisualState w) {
        w.VisualState = owner == "Player" ? VisualStates.LowHand : VisualStates.TopHand;
        w.SetHandSortingOrder();
        Command.CommandExecutionComplete();
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
