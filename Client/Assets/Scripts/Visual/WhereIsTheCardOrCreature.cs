using System;
using UnityEngine;

public enum VisualStates {
    Transition,
    LowHand, 
    TopHand,
    LowTable,
    TopTable,
    Dragging
}

public class WhereIsTheCardOrCreature : MonoBehaviour {
    
    private HoverPreview hover;
    private Canvas canvas;
    private int TopSortingOrder = 500;
    private int slot = -1;
    
    public int Slot {
        get => slot;
        set {
            slot = value;
            /*if (value != -1)
            {
                canvas.sortingOrder = HandSortingOrder(slot);
            }*/
        }
    }

    private VisualStates state;
    public VisualStates VisualState
    {
        get => state;

        set
        {
            state = value;
            if (hover == null)
                return;
            switch (state)
            {
                case VisualStates.LowHand:
                    hover.ThisPreviewEnabled = true;
                    break;
                case VisualStates.LowTable:
                case VisualStates.TopTable:
                    hover.ThisPreviewEnabled = true;
                    break;
                case VisualStates.Transition:
                    hover.ThisPreviewEnabled = false;
                    break;
                case VisualStates.Dragging:
                    hover.ThisPreviewEnabled = false;
                    break;
                case VisualStates.TopHand:
                    hover.ThisPreviewEnabled = false;
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }
    }

    private void Awake()
    {
        hover = GetComponent<HoverPreview>();
        if (hover == null)
            hover = GetComponentInChildren<HoverPreview>();
        canvas = GetComponentInChildren<Canvas>();
    }

    public void BringToFront() {
        canvas.sortingOrder = TopSortingOrder;
        canvas.sortingLayerName = "AboveEverything";
    }
    
    public void SetHandSortingOrder() {
        if (slot != -1)
            canvas.sortingOrder = HandSortingOrder(slot);
        canvas.sortingLayerName = "Cards";
    }

    public void SetTableSortingOrder() {
        canvas.sortingOrder = 0;
        canvas.sortingLayerName = "Creatures";
    }

    private int HandSortingOrder(int placeInHand) => (placeInHand + 1) * 10;
}
