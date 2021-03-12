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

public class ObjectVisualState : MonoBehaviour {
    
    private HoverPreview _hover;
    private Canvas _canvas;
    private const int TopSortingOrder = 500;

    public int Slot { get; set; } = -1;

    private VisualStates _state;
    public VisualStates VisualState {
        get => _state;
        set {
            _state = value;
            switch (_state) {
                case VisualStates.LowHand:
                    _hover.ThisPreviewEnabled = true;
                    break;
                case VisualStates.LowTable:
                case VisualStates.TopTable:
                    _hover.ThisPreviewEnabled = true;
                    break;
                case VisualStates.Transition:
                    _hover.ThisPreviewEnabled = false;
                    break;
                case VisualStates.Dragging:
                    _hover.ThisPreviewEnabled = false;
                    break;
                case VisualStates.TopHand:
                    _hover.ThisPreviewEnabled = false;
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }
    }

    private void Awake() {
        _hover = GetComponent<HoverPreview>();
        if (_hover == null)
            _hover = GetComponentInChildren<HoverPreview>();
        _canvas = GetComponentInChildren<Canvas>();
    }

    public void BringToFront() {
        _canvas.sortingOrder = TopSortingOrder;
        _canvas.sortingLayerName = "AboveEverything";
    }
    
    public void SetHandSortingOrder() {
        if (Slot != -1)
            _canvas.sortingOrder = HandSortingOrder(Slot);
        _canvas.sortingLayerName = "Cards";
    }

    public void SetTableSortingOrder() {
        _canvas.sortingOrder = 0;
        _canvas.sortingLayerName = "Creatures";
    }

    private static int HandSortingOrder(int placeInHand) => ((placeInHand + 1) * 10);
}
