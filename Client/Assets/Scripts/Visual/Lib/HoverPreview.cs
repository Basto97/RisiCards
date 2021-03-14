using UnityEngine;
using System.Linq;
using DG.Tweening;

public class HoverPreview: MonoBehaviour {
    public GameObject turnThisOffWhenPreviewing;
    public Vector3 targetPosition;
    public float targetScale;
    public GameObject previewGameObject;
    public bool activateInAwake;
    
    private static HoverPreview _currentlyViewing;
    
    private static bool _previewsAllowed = true;
    public static bool PreviewsAllowed
    {
        get => _previewsAllowed;
        set {
            _previewsAllowed= value;
            if (!_previewsAllowed)
                StopAllPreviews();
        }
    }

    private bool _thisPreviewEnabled;
    public bool ThisPreviewEnabled
    {
        get => _thisPreviewEnabled;

        set { 
            _thisPreviewEnabled = value;
            if (!_thisPreviewEnabled)
                StopThisPreview();
        }
    }

    public bool OverCollider { get; set;}
    
    private void Awake() => ThisPreviewEnabled = activateInAwake;

    private void OnMouseEnter() {
        OverCollider = true;
        if (PreviewsAllowed && ThisPreviewEnabled)
            PreviewThisObject();
    }

    private void OnMouseExit() {
        OverCollider = false;
        if (!PreviewingSomeCard())
            StopAllPreviews();
    }
    
    private void PreviewThisObject() {
        StopAllPreviews();
        _currentlyViewing = this;
        previewGameObject.SetActive(true);
        if (turnThisOffWhenPreviewing!=null)
            turnThisOffWhenPreviewing.SetActive(false);
        previewGameObject.transform.localPosition = Vector3.zero;
        previewGameObject.transform.localScale = Vector3.one;
        previewGameObject.transform.DOLocalMove(targetPosition, 1f).SetEase(Ease.OutQuint);
        previewGameObject.transform.DOScale(targetScale, 1f).SetEase(Ease.OutQuint);
    }

    public void StopThisPreview() {
        previewGameObject.SetActive(false);
        previewGameObject.transform.DOComplete();
        previewGameObject.transform.localScale = Vector3.one;
        previewGameObject.transform.localPosition = Vector3.zero;
        if (turnThisOffWhenPreviewing!=null)
            turnThisOffWhenPreviewing.SetActive(true); 
    }

    private static void StopAllPreviews() {
        if (_currentlyViewing == null) return;
        _currentlyViewing.StopThisPreview();
    }

    private static bool PreviewingSomeCard() => PreviewsAllowed && FindObjectsOfType<HoverPreview>().Any(hb => hb.OverCollider && hb.ThisPreviewEnabled);
}
