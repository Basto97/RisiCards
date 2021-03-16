using UnityEngine;
using System.Collections.Generic;
using System.Linq;
using DG.Tweening;

public abstract class HandVisual : MonoBehaviour {
    
    [SerializeField] protected Transform drawPreviewSpot;
    [SerializeField] protected Transform deckTransform;
    protected readonly List<GameObject> cardsInHand = new List<GameObject>();
    protected HandSlots slots;

    private void Awake() => slots = GetComponentInChildren<HandSlots>();
    
    public GameObject GetCardWithID(int id) => cardsInHand.FirstOrDefault(c => c.GetComponent<VisualStateManager>().ID == id);

    protected void AddCard(GameObject card) {
        cardsInHand.Insert(0, card);
        card.transform.SetParent(slots.transform);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }
    
    protected void RemoveCard(GameObject card) {
        cardsInHand.Remove(card);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }
    
    protected void RemoveCardAtIndex(int index) {
        cardsInHand.RemoveAt(index);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }

    private void UpdatePlacementOfSlots() {
        if (cardsInHand.Count == 0) return;
        slots.gameObject.transform.DOLocalMoveX((slots.transforms[0].transform.localPosition.x - slots.transforms[cardsInHand.Count - 1].transform.localPosition.x) / 2f, 0.3f);
    }

    private void PlaceCardsOnNewSlots() {
        foreach (GameObject g in cardsInHand) {
            g.transform.DOLocalMoveX(slots.transforms[cardsInHand.IndexOf(g)].transform.localPosition.x, 0.3f);
            VisualStateManager w = g.GetComponent<VisualStateManager>();
            w.Slot = cardsInHand.IndexOf(g);
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
