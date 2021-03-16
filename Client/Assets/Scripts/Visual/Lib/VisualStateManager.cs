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

public class VisualStateManager : MonoBehaviour {
    
    private HoverPreview _hover;
    private Canvas _canvas;
    
    private void Awake() {
        _hover = GetComponent<HoverPreview>();
        if (_hover == null)
            _hover = GetComponentInChildren<HoverPreview>();
        _canvas = GetComponentInChildren<Canvas>();
    }

    public int ID { get; set; }
    public int Slot { get; set; }

    private VisualStates _state;
    public VisualStates VisualState {
        set {
            _state = value;
            if (_hover == null)
                return;
            _hover.ThisPreviewEnabled = _state switch {
                VisualStates.LowHand => true,
                VisualStates.LowTable => false,
                VisualStates.TopTable => true,
                VisualStates.Transition => false,
                VisualStates.Dragging => false,
                VisualStates.TopHand => false,
                _ => throw new ArgumentOutOfRangeException()
            };
        }
    }

    public void BringToFront() {
        _canvas.sortingOrder = 500;
        _canvas.sortingLayerName = "AboveEverything";
    }
    
    public void SetHandSortingOrder() {
        _canvas.sortingOrder = -Slot;
        _canvas.sortingLayerName = "Cards";
    }

    public void SetTableSortingOrder() {
        _canvas.sortingOrder = 0;
        _canvas.sortingLayerName = "Minions";
    }
}
