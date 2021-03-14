using UnityEngine;
using System.Collections.Generic;
using System.Linq;
using DG.Tweening;

public class HandVisual : MonoBehaviour {

    public string owner = "Player";
    
    public bool playerHand = true;
    public SameDistanceChildren slots;
    
    public Transform drawPreviewSpot;
    public Transform deckTransform;

    private readonly List<GameObject> cardsInHand = new List<GameObject>();

    public OneCardManager GetCardWithID(int id) => cardsInHand.Select(card => card.GetComponent<OneCardManager>()).FirstOrDefault(ocm => ocm.card.ID == id);

    public void GivePlayerACard(Card c , bool fast = false) {
        GameObject card;
        if (playerHand) {
            card = Instantiate(c.minion ? Prefabs.Instance.minionCard : Prefabs.Instance.spellCard, deckTransform.position, Quaternion.Euler(new Vector3(0f, -179f, 0f)));
            OneCardManager manager = card.GetComponent<OneCardManager>();
            manager.ReadCardFromAsset(c);
        }
        else {
            card = Instantiate(Prefabs.Instance.cardBack, deckTransform.position, Quaternion.Euler(new Vector3(0f, 0f, 0f)));
        }
        GiveUserACard(card, fast);
    }

    private void GiveUserACard(GameObject card, bool fast) {
        AddCard(card);
        
        WhereIsTheCardOrCreature w = card.GetComponent<WhereIsTheCardOrCreature>();
        w.BringToFront();
        w.Slot = 0;
        Sequence s = DOTween.Sequence();
        if (!fast) {
            s.Append(card.transform.DOMove(drawPreviewSpot.position, GlobalSettings.CardTransitionTime));
            s.Insert(0f, playerHand
                ? card.transform.DORotate(Vector3.zero, GlobalSettings.CardTransitionTime)
                : card.transform.DORotate(card.transform.eulerAngles, GlobalSettings.CardTransitionTime));
            s.AppendInterval(GlobalSettings.CardPreviewTime);
            s.Append(card.transform.DOLocalMove(slots.Children[0].transform.localPosition, GlobalSettings.CardTransitionTime));
        } else {
            s.Append(card.transform.DOLocalMove(slots.Children[0].transform.localPosition, GlobalSettings.CardTransitionTimeFast));
            if (playerHand)    
                s.Insert(0f,card.transform.DORotate(Vector3.zero, GlobalSettings.CardTransitionTimeFast)); 
        }
        s.OnComplete(()=>ChangeLastCardStatusToInHand( w));
    }

    // UTILS
    
    public void AddCard(GameObject card) {
        cardsInHand.Insert(0, card);
        card.transform.SetParent(slots.transform);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }
    
    public void RemoveCard(GameObject card) {
        cardsInHand.Remove(card);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }
    
    public void RemoveCardAtIndex(int index) {
        cardsInHand.RemoveAt(index);
        PlaceCardsOnNewSlots();
        UpdatePlacementOfSlots();
    }

    private void UpdatePlacementOfSlots() {
        float posX = cardsInHand.Count > 0
            ? (slots.Children[0].transform.localPosition.x - slots.Children[cardsInHand.Count - 1].transform.localPosition.x) / 2f
            : 0f;
        slots.gameObject.transform.DOLocalMoveX(posX, 0.3f);
    }
    
    private void PlaceCardsOnNewSlots() {
        foreach (GameObject g in cardsInHand) {
            g.transform.DOLocalMoveX(slots.Children[cardsInHand.IndexOf(g)].transform.localPosition.x, 0.3f);
            WhereIsTheCardOrCreature w = g.GetComponent<WhereIsTheCardOrCreature>();
            w.Slot = cardsInHand.IndexOf(g);
            w.SetHandSortingOrder();
        }
    }
    
    private void ChangeLastCardStatusToInHand(WhereIsTheCardOrCreature w) {
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
