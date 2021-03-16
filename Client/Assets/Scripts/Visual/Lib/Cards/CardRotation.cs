using UnityEngine;

[ExecuteInEditMode]
public class CardRotation : MonoBehaviour {
    
    public RectTransform cardFront;
    public RectTransform cardBack;
    public Transform targetFacePoint;
    public Collider col;
    
    private bool _showingBack;

    private Camera _cam;

    private void Awake() {
        _cam = Camera.main;
        Update();
    }

    private void Update () {
        var position = _cam.transform.position;
        var position1 = targetFacePoint.position;
        RaycastHit[] hits = Physics.RaycastAll(origin: position, direction: (-position + position1).normalized, maxDistance: (-position + position1).magnitude);
        bool passedThroughColliderOnCard = false;
        foreach (RaycastHit h in hits)
            if (h.collider == col)
                passedThroughColliderOnCard = true;
        if (passedThroughColliderOnCard == _showingBack) return;
        _showingBack = passedThroughColliderOnCard;
        if (_showingBack) {
            cardFront.gameObject.SetActive(false);
            cardBack.gameObject.SetActive(true);
        } else {
            cardFront.gameObject.SetActive(true);
            cardBack.gameObject.SetActive(false);
        }
    }
}